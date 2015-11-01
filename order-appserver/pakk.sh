#!/bin/bash
cd "$( dirname "${BASH_SOURCE[0]}" )"
set -e

#Delete old files
rm -rf package

#Create directory structure
mkdir package
mkdir package/a01
mkdir package/a01/order-front

mkdir package/var
mkdir package/var/log
mkdir package/var/log/order-front

#Copy web application
cp order-front2/target/order-front.jar package/a01/order-front

#Copy systemd unit file
cp order-appserver.service package/a01/order-front
