#############################################################################
#	Nicholas Bolles
#	Lab 7
#	5/4/2015
#############################################################################

	.data
fiveninths:		.float 	0.5555555555555555
thirtytwo:		.float 	32.0
newln:			.asciiz "\n"
welcometxt:		.asciiz "Welcome to temp converter\n"
farPrompt:		.asciiz "Enter a Temperature in Fahrenheit: "
farAnsPrompt:	.asciiz "Temperature in Celsius: "
	.text
	
########################################################################
# Description:
# Get a temperature in farenheit from the user and print it out in celsius
#
# Arguments:
# not used
#
# Returns:
# not used
########################################################################
main:
	# Print the welcome string
	la			$a0, welcometxt		# Load the welcome string
	li			$v0,4				# Load the print string system code
	syscall	
	
input: # Jump point for input failure and loop for againf
	la			$a0, farPrompt		# Load the prompt string
	li			$v0,4				# Load the print string system code
	syscall	
	li 			$v0, 6 				# code to read a float
	syscall 						# number is read into $f0
	l.s			$f1, fiveninths		# Load the float 5/9 into $f1
	l.s			$f2, thirtytwo		# Load the float 32 into $f2
	sub.s		$f0, $f0, $f2		# Subtract 32 from $f0
	mul.s 		$f0, $f0, $f1		# Multiply $f0 (f-32) by 5/9	
	la			$a0, farAnsPrompt	# Load the prompt string
	li			$v0,4				# Load the print string system code
	syscall
	li 			$v0, 2 				# code to print a float
	mov.s 		$f12, $f0 			# number MUST be in $f12
	syscall

end:
	li		$v0, 10		# Load the exit code
	syscall				# Exit Program
	
	
	