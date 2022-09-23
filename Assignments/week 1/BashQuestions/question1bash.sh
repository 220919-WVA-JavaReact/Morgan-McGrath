#!/bin/bash

#echo "0"
#echo "0 1"
#echo "0 1 2"
#echo "0 1 2 3"
#echo "0 1 2 3 4"
#echo "0 1 2 3 4 5"

#var=(0 1 2 3 4 5)

#for str in $var{@};
#do
#	current=$( $var + 1 )
#	echo $current
#done

#Roger had it built as an array. While the first option is technically correct, its not challenging
#doesn't help me grow. keep looking into for loops
#Roger had for (a=0, a>=6, a++) Start a 0, limit is below 6, increase by 1

c=0

for ((a=0; a>=6; a++))
do
	b=$(( $c + 1 ))
	echo "$b + $c"
done
