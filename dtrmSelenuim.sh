./go clean
./go build
./go prep-release-zip
find . -name '*.zip' -print
