#!/bin/bash


echo Please enter the following information;
	read -p 'First Name: ' fname #-p puts in the next line as a promp, allowing it to be a var
	read -p 'Last Name: ' lname
	read -p 'Username: ' uname
	read -p 'Email: ' email

#id='0' #Goal is to make it read the id of the previous input on mock_data.csv and continue the id assignment

#x=$(tail -n1 $1) #reads the last line of whatever doc is $1
exec < $1
read header
while IFS="," read -r id fname2 lname2 email2 uname2
do
#	y=$($id + 1) #It's supposed to mark y as $id+1
	y=$id
#echo $y
done
#echo $y

	z=$(( $y + 1 ))

#	if [ $id -eq $id ]
#	then
#		id=(($id+1))
#		continue
#	fi
	echo $z,$fname,$lname,$email,$uname >> mock_data.csv
#	id=$((id+1))
