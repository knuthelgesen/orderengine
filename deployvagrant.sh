#!/bin/bash
cd "$( dirname "${BASH_SOURCE[0]}" )"
set -e

source scripts/deploy.sh
setValues $@

function deploy {
host=$1
        ssh $host "sudo dpkg --purge order-engine; sudo dpkg -i /vagrant/package.deb"
}

if [ "$order1" == "true" ]; then
        deploy order-vagrant1
fi

if [ "$order2" == "true" ]; then
        deploy order-vagrant2
fi
