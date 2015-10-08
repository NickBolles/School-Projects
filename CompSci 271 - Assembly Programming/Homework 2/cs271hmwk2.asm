#############################################################################
#	Nicholas Bolles
#	Homework 2
#	3/11/2015
#############################################################################

	.data
integer:	.word 12
newln:	.asciiz "\n"
counter:	.asciiz "   Counter \n"
inputtxt:	.asciiz "Please Enter How Many Of Fibonacci Numbers you want\nnote:Must be above 0, and not too high or an arithmatic exception will occur\n"
zeroerrortext:	.asciiz "Error: input is negative or is 0, Please enter only positive numebrs\n"
sectiontext:	.asciiz "=======================================================================\n"
anothertxt:	.asciiz "Enter 1 to do another or 0 to exit\n"
array:	.word 0,0,0,0,0,0,0,0,0,0,0,0
	.text
	
########################################################################
# Description:
# Ask the user how many Fibonacci numbers they would like and return the
# numbers to the user. Then ask the user if they would like to do it 
# again, clearing the array and running through the program again.
#
# Arguments:
# $a0 = not used
#
# Returns:
# $v0 = not used
# $v1 = not used
########################################################################
main:
	b			input
zeroinput:
	la			$a0, zeroerrortext		# Load the input string
	li			$v0,4				# Load the print string system code
	syscall	
	jal printseperator
	b			input
input: # Jump point for input failure and loop for again
	la			$a0, array
	jal			resetarray	
	# Ask the user how many numbers they would like
	la			$a0, inputtxt		# Load the input string
	li			$v0,4				# Load the print string system code
	syscall	
	# Get the user input
	li			$v0, 5
	syscall	
	move		$s1, $v0
	jal printseperator
	# li		$s1, 12			# To restrict it to 0-12 uncomment this line
	# ble		$s1, $s1, input 	# retry if the input is >12
	blez		$s1, zeroinput 	# retry if the input is <=0
	
	# Get the Fib #'s
	move		$a0, $s1			# Load the ammount of fib numbers
	la			$a1, array			# Load the address of the array
	jal			fib	
	
	# Print the array
	la			$a0, array			# Load the array into $a0
	jal			printarray			# Print the array	

	jal printseperator
	
	# Ask the user if they would like to go again
	la			$a0, anothertxt		# Load the input string
	li			$v0,4				# Load the print string system code
	syscall	
	# Get the user input
	li			$v0, 5
	syscall	
	bnez		$v0, input 			# go to input if $v0 is not 0 else it will go on to end
	
	b		end

	
########################################################################
# Description:
# Adds the specified number of Fibonacci numbers into the specified array
#
# Arguments:
# $a0 = Number of Fibonacci numbers to add to the array
# $a1 = The array to add the numbers to
#
# Returns:
# $v0 = not used
# $v1 = not used
########################################################################
fib:
	# $a0 will be the number of fib #s to put into the array, which is $a1
	move		$t0, $a0		# copy $a0, or n to $t0
	addi		$t0, -1			# reduce n by one so that the loop runs n-1 times
	li			$t1, 0			# Initiate the loop counter with 0, so that it loops n-1 times
	li			$t2, 0			# The number that is n-2
	li			$t3, 1			# The number that is n-1	
	li			$t4, 0			# this will be the temp result for the new fib number 
	sw			$t3, 0($a1)		# Save the first fib number into the array
	
fibloop:
	addi		$t1, $t1, 1		# Increment the loop counter
	#compute the next number
	add			$t4,$t3,$t2		# add the last two numbers together
	move		$t2, $t3		# move n-1($t3) to n-2($t2)
	move		$t3, $t4		# move n-1($t3) to n-2($t2)
	
	#Find the correct adress for the array slot
	li			$t5, 4			
	mult		$t1, $t5		# Find the offset of the element
	mflo		$t6				# Load the result of the offset into $t6
	add			$t7, $t6, $a1	# Load the address of this element into $t7
	# Save the Result
	sw			$t3, 0($t7)		# Save the result of the function-$t4- into the correct array slot
	
	
	###########################################################################
	#Debug Code
	# Loop counter
	# move		$a0, $t1		# Load the loop counter
	# li			$v0,1			# Load the print int system code
	# syscall	
	# la			$a0, counter	# Load the new line data item
	# li			$v0,4			# Load the print string system code
	# syscall	
	############################################################################
	
	ble			$t1, $t0, fibloop
fibloopend:
	jr			$ra
	
		
########################################################################
# Description:
# Computes the length of an array
#
# Arguments:
# $a0 = The array to determine the length of
#
# Returns:
# $v0 = integer of the length of the array
# $v1 = not used
########################################################################
length:
	li			$v0,-2
	move		$t1,$a0
lengthloop:
	addi		$v0,$v0, 1		# Increment the array length by 1
	lw			$t0,0($t1)
	addi		$t1,$t1, 4
	bnez		$t0, lengthloop		
	jr			$ra
	
########################################################################
# Description:
# Prints an array of integers out to the console
#
# Arguments:
# $a0 = The array to print
#
# Returns:
# $v0 = not used
# $v1 = not used
########################################################################
printarray:
	move		$t7, $a0		# Move the array to register $t7
	sw			$ra, 0($sp)		# Save the return address into $sp[0]
	jal 		length
	addi		$t1, $v0, -1	# Load the length into $t1, and subtract one
	li			$t2, -1			# Initiate the loop counter
printarrayloop:
	addi		$t2, $t2, 1		# Increment the loop counter
	li			$t6, 4			
	mult		$t2, $t6		# Find the offset of the element
	mflo		$t3				# Load the result of the offset into $t3
	add			$t4, $t3, $t7	# Load the address of this element into $t4
	lw			$a0, 0($t4)		# Load the element into $a0
	li			$v0,1			# Load the print int system code
	syscall	
	la			$a0, newln		# Load the new line data item
	li			$v0,4			# Load the print string system code
	syscall	
	
	
	###########################################################################
	# Debug Code
	# Loop counter
	# move		$a0, $t2		# Load the loop counter
	# li		$v0,1			# Load the print int system code
	# syscall	
	# la		$a0, counter	# Load the new line data item
	# li		$v0,4			# Load the print string system code
	# syscall	
	###########################################################################
	
	
	blt			$t2, $t1, printarrayloop
printarrayloopend:
	lw			$ra, 0($sp)		# Load the return address from $sp[0]
	jr			$ra
	

########################################################################
# Description:
# Resets the array to all 0s
#
# Arguments:
# $a0 = The array to reset
#
# Returns:
# $v0 = not used
# $v1 = not used
########################################################################
resetarray:
	move		$t1,$a0
	li			$t2, 0
resetarrayloop:
	lw			$t0,0($t1)
	sw			$t2,0($t1)			# save $t2(0) to array[i]
	addi		$t1,$t1, 4
	bnez		$t0, resetarrayloop		
	jr			$ra
	
########################################################################
# Description:
# prints seperations
#
# Arguments:
# $a0 = not used
#
# Returns:
# $v0 = not used
# $v1 = not used
########################################################################
printseperator:
	la			$a0, sectiontext# Load the new line data item
	li			$v0,4			# Load the print string system code
	syscall	
	jr			$ra
	
end:
	li		$v0, 10		# Load the exit code
	syscall				# Exit Program
	
	
	