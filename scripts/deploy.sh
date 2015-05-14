#!/bin/bash

function setValues {
order1=false;
order2=false;
order3=false;
precompile=false;
	
while getopts ":123p" Option
do
	case $Option in
		1) order1=true; echo "Deploying to order1";;
		2) order2=true; echo "Deploying to order2";;
		3) order3=true; echo "Deploying to order3";;
		p) precompile=true; echo "Deploying precompiled jsps";;
		?) usage; exit;;
esac
done
if [ $# -eq 0 ]; then
order1=true;
order2=true;
order3=true;
echo "Deploying to all servers"; fi

export order1;
export order2;
export order3;
export precompile;

}

function usage {
	echo "-1 deploy to order1";
	echo "-2 deploy to order2";
	echo "-3 deploy to order3";
	echo "-p precompile jsp files, faster load time, can't hotdeploy jsp";
}
