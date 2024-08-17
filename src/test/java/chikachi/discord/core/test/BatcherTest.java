package chikachi.discord.core.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.google.common.collect.Lists;

import chikachi.discord.core.Batcher;

public class BatcherTest {

    @Test
    @SuppressWarnings("unchecked")
    public void testBatcher() {
        final List<String> expected = Lists.newArrayList("foo", "bar", "baz");
        AtomicBoolean consumed = new AtomicBoolean(false);
        Consumer<List<String>> consumer = (List<String> list) -> {
            assertThat(consumed.get(), is(false));
            assertThat(list, is(expected));
            consumed.set(true);
        };
        ScheduledExecutorService mockExecutor = mock(ScheduledExecutorService.class);
        Batcher<String> batcher = new Batcher<>(consumer, 1000, 3, mockExecutor);

        ScheduledFuture firstScheduledFuture = mock(ScheduledFuture.class);
        when(mockExecutor.schedule((Runnable) any(), eq(1000L), eq(TimeUnit.MILLISECONDS)))
                .thenReturn(firstScheduledFuture);

        batcher.queue("foo");

        verify(mockExecutor, times(1)).schedule((Runnable) any(), eq(1000L), eq(TimeUnit.MILLISECONDS));

        ScheduledFuture secondScheduledFuture = mock(ScheduledFuture.class);
        when(mockExecutor.schedule((Runnable) any(), eq(1000L), eq(TimeUnit.MILLISECONDS)))
                .thenReturn(secondScheduledFuture);

        batcher.queue("bar");

        verify(firstScheduledFuture, times(1)).cancel(false);
        verify(mockExecutor, times(2)).schedule((Runnable) any(), eq(1000L), eq(TimeUnit.MILLISECONDS));

        verify(mockExecutor, never()).schedule((Runnable) any(), eq(1L), eq(TimeUnit.MILLISECONDS));
        batcher.queue("baz");

        verify(secondScheduledFuture, times(1)).cancel(false);
        ArgumentCaptor<Runnable> argument = ArgumentCaptor.forClass(Runnable.class);
        verify(mockExecutor, times(1)).schedule(argument.capture(), eq(1L), eq(TimeUnit.MILLISECONDS));

        assertThat(consumed.get(), is(false));
        argument.getValue().run();
        assertThat(consumed.get(), is(true));
    }
}
