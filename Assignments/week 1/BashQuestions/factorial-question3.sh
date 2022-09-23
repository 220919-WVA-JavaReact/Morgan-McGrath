#!/bin/bash

echo "Enter a number"
read var

fact=1
while [ $var -gt 1 ] #limits the loop to keeping the argument above 1
do
	fact=$((fact * var)) #redefines fact as result of original number by old fact
	var=$((var - 1)) #changes the number to be one less each time
done
	echo $fact
