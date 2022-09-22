#!/bin/bash

a=${1}
b=${2}
if [[${1}-gt${2}]]
then let c=$a/$b
elif [[${2}-gt${1}]]
then let c=$b/$a
else "something is wrong here"
echo $c
fi

