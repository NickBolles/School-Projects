########################################################################
# Nicholas Bolles
# COMPSCI 271 Lab 5
# March 16-17, 2015
########################################################################
# Main program description:
# Read 3 integers from the command line, sort them using the order3
# function, then print the sorted values.
#
# Arguments: None.
#
# Returns: Nothing.
########################################################################

        .data
prmpt1: .asciiz "Enter first number : "
prmpt2: .asciiz "Enter second number: "
prmpt3: .asciiz "Enter third number : "
lowmsg: .asciiz "Lowest number : "
midmsg: .asciiz "\nMiddle number : "
himsg:  .asciiz "\nHighest number: "

        .text
main:	# Get first number from user (store in $s0 temporarily)
		li      $v0, 4
		la      $a0, prmpt1
		syscall
		li      $v0, 5
		syscall
		move    $s0, $v0     

		# Get second number from user, store in $a1				 
		li      $v0, 4
		la      $a0, prmpt1
		syscall
		li      $v0, 5
		syscall
		move    $a1, $v0     
		# Get third number from user, store in $a2
		li      $v0, 4
		la      $a0, prmpt1
		syscall
		li      $v0, 5
		syscall
		move    $a2, $v0     
		# Move first number back into $a0
		move    $a0, $s0     
		# Now all three numbers should be in $a0, $a1, $a2;
		# call function to order the three numbers
		jal     order3
		
		# Save value returned in $a0 somewhere else,
		# since $a0 is used to display message
		move    $s0, $a0     
		# Display lowest number
		li      $v0, 4
		la      $a0, lowmsg
		syscall
		li		$v0, 1
		move	$a0, $s0
		syscall
		# Display middle number
		li      $v0, 4
		la      $a0, midmsg
		syscall
		li		$v0, 1
		move	$a0, $a1
		syscall
		# Display highest number
		li      $v0, 4
		la      $a0, himsg
		syscall
		li		$v0, 1
		move	$a0, $a2
		syscall
		# End program
		li      $v0, 10
		syscall
		
		
########################################################################
# Description:
# Given 3 numbers, order the numbers from lowest to highest.
#
# Arguments:
# $a0, $a1, $a2 = 3 positive integers, one in each register
#
# Returns:
# $a0 = lowest number
# $a1 = middle (median) number
# $a2 = highest number
########################################################################

order3:	
		li		$t1, 0				#initialize $t1 to 0
	compa0a1:
		blt		$a0,$a1,compa1a2    #Check if $a1 < $a0
		move	$t0, $a0			#Cache $a0 into $a4
		move	$a0, $a1			#Move $a1 to $a2
		move	$a1, $t0			#Move the Cached value of $a0(In $a4 now) into $a1
		bnez	$t1, order3end		#Jump to end if this is the inner loop of compa1a2
	compa1a2:
		blt		$a1, $a2, order3end
		move	$t0, $a1			#Cache $a1 into $a4
		move	$a1, $a2			#Move $a2 to $a1
		move	$a2, $t0			#Move the Cached value of $a1(In $a4 now) into $a2
		li		$t1, 1				#Change $t1 to 1 because its the inner loop, and to avoid an infinite loop
		b		compa0a1			#Compare $a0,$a1 again
	order3end:
		jr		$ra




