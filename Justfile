set windows-shell := ["nu", "-c"]

gradle-clean:
    ./gradlew --stop
    ./gradlew clean

clean: gradle-clean

spotless:
    ./gradlew :spotlessApply

build: clean spotless
    ./gradlew build

build-server: build
    mkdir run
    mkdir run/mods
    echo "eula=true" o> run/eula.txt
    echo "level-seed=-6202107849386030209\nonline-mode=true\n" o> run/server.properties
    echo "stop" o> run/stop.txt


run-server:
    ./gradlew --build-cache --info --stacktrace runServer