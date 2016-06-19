#!/usr/bin/env bash
#gradle clean test
#exit 0

fail(){
    echo "$@"
    exit -1
}
pass(){
    echo "$@"
}
log(){
    echo "$@"
}
log "Build artifacts..."
(gradle > /dev/null && pass "Artifacts were created.") || fail "There is en error in gradle build"
log "Create docker image..."
(docker build -t extsoft/allure-docker-example . && pass "Image was created.") || fail "Unable to create docker image"
log "Run image..."
docker run -t -i -p 8000:80 extsoft/allure-docker-example
