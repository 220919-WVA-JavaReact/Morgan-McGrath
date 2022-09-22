#!/bin/bash

#Goal1: adding a timestamp per use for record keeping purposes - achieved
#Goal2: allow for use of negative numbers
#Goal3: have code automatically shift to AnswerKey.txt to keep history of use

timestamp=$(date +%c) 
echo $timestamp

#note: negative numbers do not work, the system is not recognizing the dash marking it as negative
#note contd: [+-] doesn't work in the if statement, gotta adjust the statement
if [[ ${1} =~ ^[0-9]+$ ]] && [[ ${2} =~ ^[0-9]+$ ]]; #note: this specifies that both ${1} and ${2} are actual numbers
then
 let "a=${1}+${2}";
 echo ${1}+${2}=$a;
else 
 echo "Positive Integers only please"
fi

if [[ ${1} =~ ^[0-9]+$ ]] && [[ ${2} =~ ^[0-9]+$ ]]
then
 let b=${1}-${2}
 echo ${1}-${2}=$b 
else 
 echo "Positive Integers only please" 
fi

# * is multiplication, / is division, % is keeping the remainder

if [[ ${1} =~ ^[0-9]+$ ]] && [[ ${2} =~ ^[0-9]+$ ]]
then
 let c=${1}*${2}
 echo ${1}*${2}=$c
else
 echo "Positive Integers only please"
fi


#note: the below code was used to ensure there were no 0s in the end result by swapping ${1} and ${2} if ${2} was bigger.
#if [[ ${1} =~ ^[0-9]+$ ]] && [[ ${2} =~ ^[0-9]+$ ]] && [[ ${1} -gt ${2} ]] && [[ ${2} -lt ${1} ]]
#then
# let d=${1}/${2}
# echo ${1}/${2}=$d
#elif [[ ${1} -lt ${2} ]] && [[ ${2} -gt ${1} ]]
#then
# let d=${2}/${1}
# echo ${2}/${1}=$d
#else 
# echo "Numbers only please"
#fi

if [[ ${1} =~ ^[0-9]+$ ]] && [[ ${2} =~ ^[0-9]+$ ]] 
then
 echo ${1}/${2}=$(( 100*${1}/${2} )) | sed -e 's/..$/.&/;t' #note: sed is a statement editor that allows for changing of the implied statement. In this case, its used to shift the results into decimals
else
 echo "Positive Integers only please"
fi

#note: next section is to have it shift to AnswerKey.txt
#MYVAR=${1} && ${2}
#ANSWR=$a && $b && $c && $d
#if [[ $a =~ ^[0-9]+$ ]] && [[ $b =~ ^[0-9]+$ ]] && [[ $c =~ ^[0-9]+$ ]] && [[ $d =~ ^[0-9]+$ ]]
#then
# $MYVAR >> AnswerKey.txt
# $ANSWR >> AnswerKey.txt
 echo +-----------------------------------------------+
#else
# echo "${0} has had an error. Please make sure that you only input positive integers"
#fi
