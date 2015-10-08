#############################################################################
#	Nicholas Bolles
#	Homework 3
#	4/21/2015
#############################################################################

	.data
newln:			.asciiz "\n"
introPrompt:	.asciiz "Finding the min and max of array: \n "
maxPrompt:		.asciiz "The Max number is:      "
minPrompt:		.asciiz "The Min number is:      "
divBy:			.asciiz "Values divisible by "   
divBy2:			.asciiz " : "
sectiontext:	.asciiz "=======================================================================\n"
comma:			.asciiz ", "
X:				.word 	31,17,92,46,172,208,13,93,65,112
# N:  This is replaced with a more abstract jal length
DivBy:			.word	4
	.text
########################################################################
# Description:
# Process an array, finding the min, the max, and how many numbers are divisible by 4
#
# Arguments:
# $a0 = not used
#
# Returns:
# $v0 = not used
# $v1 = not used
########################################################################
main:
		# print the intro message
			la			$a0, introPrompt		# Load the prompt string
			li			$v0,4					# Load the print string system code
			syscall		

		# Print the array out
			la	$a0, X
			jal printarray

        # Load address of X into a $s0
			la	$s0, X
		# Load the address of X into $a0 and find the length
			la	$a0, X
			jal length
			move $s1, $v0						# store the length of the array into $s1
		# Prepare the stack frame
			addi		$sp,$sp, 8				# Allocate Stack Space
			sw			$s0, 0($sp)				# Save the address of the array in $sp[0]
			sw			$s1, 4($sp)				# Save the number of items in the array in $sp[1]
			
        # Call the MinMax function
			jal 		MinMax
		# Copy returned value from the MinMax function into a 
		#    register, then deallocate the stack frame's space
			lw 			$s4, 8($sp)			# Load the return value from $sp[0] which is min
			lw 			$s5, 12($sp)			# Load the return value from $sp[1] which is max
			add			$sp,$sp, -8				# De-allocate the Stack Space
			
			
		# Print the min
			la			$a0, minPrompt			# Load the prompt string
			li			$v0,4					# Load the print string system code
			syscall		
			move		$a0, $s4				# Load the integer
			li			$v0,1					# Load the print int system code
			syscall		
			la			$a0, newln			# Load the prompt string
			li			$v0,4					# Load the print string system code
			syscall	
		# Print the max
			la			$a0, maxPrompt			# Load the prompt string
			li			$v0,4					# Load the print string system code
			syscall		
			move		$a0, $s5				# Load the integer
			li			$v0,1					# Load the print int system code
			syscall		
			la			$a0, newln				# Load the prompt string
			li			$v0,4					# Load the print string system code
			syscall	
			
			
		# Prepare the stack frame
			addi		$sp,$sp, 12				# Allocate Stack Space
			sw			$s0, 0($sp)				# Save the address of the array in $sp[0]
			sw			$s1, 4($sp)				# Save the number of items in the array in $sp[1]
			lw			$s1, DivBy				# Load the DivBy Ammount
			sw			$s1, 8($sp)				# Save the DivBy number in $sp[2]
			
        # Call the DivCount function
			jal 		DivCount
		# Copy returned value from the DivCount function into a 
		#    register, then deallocate the stack frame's space
			lw 			$s6, 12($sp)			# Load the return value from $sp[0] which is ammount div by the ammount
			add			$sp,$sp, -12				# De-allocate the Stack Space
		
		# Print the divBy Ammount
			la			$a0, divBy				# Load the prompt string
			li			$v0,4					# Load the print string system code
			syscall		
			lw			$a0, DivBy				# Load the integer to devide by
			li			$v0,1					# Load the print int system code
			syscall		
			la			$a0, divBy2				# Load the prompt string
			li			$v0,4					# Load the print string system code
			syscall		
			move		$a0, $s6				# Load the the result of the DivCount function
			li			$v0,1					# Load the print int system code
			syscall		
			la			$a0, newln			# Load the prompt string
			li			$v0,4					# Load the print string system code
			syscall	
					
finish: li      $v0, 10							# return control to the OS
        syscall

########################################################################
# Description:
# Find the min and max of the array
#
# Arguments:
# 0($sp): starting address of array
# 4($sp): number of array elements
#
# Returns:
# 8($sp): The Min item in the array
# 12($sp): The Max item in the array
########################################################################
        .text	
MinMax:	
			lw 			$t1, 0($sp) 			# Get start address of X
			lw 			$t2, 4($sp) 			# Get N
			li 			$t3, 1 					# init Loop Counter, start it at one 
												# because we make the first element the min and max
			lw			$t4, 0($t1)				# Initiate the Min-$t4 to X[0]
			lw			$t5, 0($t1)				# Initiate Max-$t5 to X[0]
MinMax_loop:
			ble			$t2, $t3,MinMax_LoopDone	# If N <= I then we are done
			li			$t6, 4			
			mult		$t3, $t6				# Find the offset of the element
			mflo		$t6						# Load the result of the offset into $t6
			add			$t6, $t6, $t1			# Get the address of $sp[i]
			lw			$t6, 0($t6)				# Load the word from $sp[i]
			#now $t6 is the word we want. find out if its the min or max
MinMax_CheckMax:
			bgt			$t5, $t6, MinMax_CheckMin	# if $sp[i] is less then max skip switching it
			# This number is bigger set it as max
			move		$t5, $t6				# Store this number $t6, into the Max $t5			
MinMax_CheckMin:
			blt			$t4, $t6, MinMax_LoopEnd# if $sp[i] is greater then min skip switching it
			# This number is less set it as max
			move		$t4, $t6				# Store this number $t6, into the Max $t5
MinMax_LoopEnd:			
			addi		$t3, 1					# increment the loop counter
			b			MinMax_loop				# loop
			
MinMax_LoopDone:
			sw			$t4, 8($sp)				# Store the Min
			sw			$t5, 12($sp)			# Store the Max	
			jr	$ra
	
	
########################################################################
# Description:
# Find the number of items in the array that are divisible by the third argument
#
# Arguments:
# 0($sp): starting address of array
# 4($sp): number of array elements
# 8($sp): value to divide by
#
# Returns:
# 12($sp): The number of items divisible by the third argument
########################################################################
        .text	
DivCount:	
			lw 			$t1, 0($sp) 			# Get start address of X
			lw 			$t2, 4($sp) 			# Get N
			lw 			$t3, 8($sp) 			# Get the value to devide by
			li 			$t4, 1 					# init Loop Counter, start it at one 
												# because we make the first element the min and max
			li			$t5, 0					# the counter for items divisible
DivCount_loop:
			ble			$t2, $t4,MinMax_LoopDone	# If N <= I then we are done
			li			$t7, 4			
			mult		$t4, $t7				# Find the offset of the element
			mflo		$t7						# Load the result of the offset into $t7
			add			$t7, $t7, $t1			# Get the address of $sp[i]
			lw			$t7, 0($t7)				# Load the word from $sp[i]
			#now $t7 is the word we want. find out if its divisible by $t3
			div			$t7, $t3				
			mfhi		$t7						# load the remainder into $t7
			bnez		$t7, DivCount_LoopEnd	# If the remainder is not 0 then go to the next item
			add			$t5, 1					# increment # of divisible
DivCount_LoopEnd:			
			addi		$t4, 1					# increment the loop counter
			b			DivCount_loop			# loop
DivCount_LoopDone:
			sw			$t5, 12($sp)
			jr	$ra
	
		
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
	addi		$t1, $v0, 0		# Load the length into $t1, and subtract one
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
	la			$a0, comma		# Load the new line data item
	li			$v0,4			# Load the print string system code
	syscall		
	blt			$t2, $t1, printarrayloop
printarrayloopend:
	la			$a0, newln		# Load the new line data item
	li			$v0,4			# Load the print string system code
	syscall	
	lw			$ra, 0($sp)		# Load the return address from $sp[0]
	jr			$ra
	
	