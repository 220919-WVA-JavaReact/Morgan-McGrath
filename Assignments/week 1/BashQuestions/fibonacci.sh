#!/bin/bash


f=0
i=1

for (( g=0; g<=20; g++ )) #g = sequence, starts at 0, ends at/before 12 times, inside goes up by 1 each time
do 
	if (( $f >=100 )) #limits f to a max of 100
	then
		echo 'Sequence has reached more than limit of 100'
		break
	else
		echo $f #f is printed for the first time before running through the sequence, making it 0
	fi
	b=$((${f} + ${i})) #sets the base equation for it to follow
	f=$i #allows for the above equation to be increased by the previous number
	i=$b
	#echo $f
done

