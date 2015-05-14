#!/bin/bash
cd "$( dirname "${BASH_SOURCE[0]}" )"
set -e

#Build using maven
mvn clean install

#Compile Dart to Javascript
order-statisk/bygg.sh
