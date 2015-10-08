########################################################################
# Nick Bolles
# Lab 4
# 2/26/2015
########################################################################
# Description:
# 	Translate high-level code to MIPS
########################################################################
		.data
result:	.word		4:0		# Initialize 4 Words with initial value of 0
endl:   .asciiz 	"\n"	# Declare endline utility data item
		.text
		.globl main
main: 
		li		$s1, 40		# Load the immediate value 40 into $s0 (w1)
		li		$s2, 20		# Load the immediate value 20 into $s2 (w2)	
		li		$s3, 40		# Load the immediate value 40 into $s3 (total)
		li		$s4, 0		# Load the immediate value 0  into $s4for the loop counter (i)
		li		$s5, 4		# Load the immediate value 4  into $s5 for use in multiplication of i
Loop:
		add		$s3,$s3,$s2	# Add $s2 (w2) to $s3 (total)
		
		ble		$s3, 100, Loop_End_If	# if $s3 is greater then 100 go to Loop_End_If
		#Body of if statement
		addi		$s3, $s3, -100 # subtract 100 from $s3 (total)
		#End if
Loop_End_If: 
		mult	$s4, $s5		# multiply $s4 (i) by 4 to get the correct address for result
		mflo	$s6				# Load the result of $s4*4 into $s6	
		la		$s7, result		# Load the address of the result array into $s7
		add		$s6, $s6, $s7	# add the offset ($s6) to the array address ($s7)
		sw		$s3, ($s6)		# Load the word value of the correct address from $s3 (total) 
		#Now print the results
		li		$v0, 1			# Load the Print Integer System call
		move	$a0, $s3		# Load $s3 (total) into $a0 to be printed
		syscall
		#Print the endl to start a new line in the next loop
		li		$v0, 4			# Load the Print String System call
		la		$a0, endl		# Load endl into $a0 to be printed
		syscall
		#Increment the Loop Counter (i)
		addi	$s4,$s4,1 		# increment the loop counter
		blt		$s4,4,Loop		# if $s4 (i) < 4 then loop again
		
		li		$v0, 10			# Load the return to Operating System call
		syscall					# Return control to the Operating System
		.end