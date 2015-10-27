#!/bin/bash
cd "$( dirname "${BASH_SOURCE[0]}" )"
set -e

#Delete old files
rm -rf package
mkdir package
rm -f package.deb

#Run command for subprojects
order-statisk/pakk.sh
order-appserver/pakk.sh
order-ai/pakk.sh

#Copy control files
cp -rf DEBIAN package/
#Copy files from subprojects
cp -rf order-statisk/package .
cp -rf order-appserver/package .
cp -rf order-ai/package .
cat order-appserver/DEBIAN/prerm >> package/DEBIAN/prerm
cat order-appserver/DEBIAN/postinst >> package/DEBIAN/postinst
#TODO order-ai pre/post

#Copy all files to temp dir
rm -rf /tmp/package
cp -rf package /tmp/
chmod -R 755 /tmp/package

#Build the package
fakeroot dpkg-deb --build /tmp/package

#Copy the package back
cp /tmp/package.deb .
