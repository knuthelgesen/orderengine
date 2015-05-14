#!/bin/bash
cd "$( dirname "${BASH_SOURCE[0]}" )"
set -e

#Delete old files
rm -rf package
mkdir package

#Copy files
cp -rf ./etc ./package
cp -rf ./a01 ./package
