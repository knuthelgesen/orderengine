#!/bin/bash
cd "$( dirname "${BASH_SOURCE[0]}" )"
set -e

#Delete old files
rm -rf package

#Create directory structure
mkdir package
mkdir package/a01
mkdir package/a01/order-front
mkdir package/a01/order-front/wrapper
mkdir package/a01/order-front/config
mkdir package/a01/order-front/applib
mkdir package/a01/order-front/tmp

mkdir package/var
mkdir package/var/log
mkdir package/var/log/order-front

#Copy wrapper files
cp ../wrapper-files/* package/a01/order-front/wrapper/
cp wrapper/* package/a01/order-front/wrapper/

#Copy lib files
cp applib/* package/a01/order-front/applib/

#Copy web application
cp target/order-front.war package/a01/order-front/wrapper

#Copy configuration files
cp config/* package/a01/order-front/config/
