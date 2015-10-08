#############################################################################
#	Nicholas Bolles
#	Homework 3
#	4/21/2015
#############################################################################

	.data
newln:			.asciiz "\n"
str:    .space  16
tolongprompt: .asciiz "Too Long. Please Enter a string under 16 characters \n"
prompt: .asciiz "Enter a string: "
yespal: .asciiz "This is a palindrome.\n"
nopal:  .asciiz "This is not a palindrome.\n"
spacer:  .asciiz "    "

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
			b 			input
		# print the intro message
inputtolong:
			la			$a0, tolongprompt		# Load the prompt string
			li			$v0,4					# Load the print string system code
			syscall		
input:
			la			$a0, prompt				# Load the prompt string
			li			$v0,4					# Load the print string system code
			syscall		
			la 			$a0, str				# load the string into the input buffer
			li			$a1, 16					# Load the max length, 16, into the limit
			li			$v0, 8					# Load the get String system code
			syscall
			
			addi		$sp,$sp, -12			# Allocate Stack Space
			la			$a0, str
			sw			$a0, 0($sp)				# Save the string to $sp[0]
			jal pal
			lw			$a1, 4($sp)				# Load the result of pal into $a1
			addi		$sp,$sp, 12				# De-Allocate Stack Space
			
			
			beqz		$a1,inputNotPal			# if the result of pal is 0 then it is not a palendrome
inputPal:
			la			$a0, yespal				# Load the prompt string
			li			$v0,4					# Load the print string system code
			syscall
			b			end
inputNotPal:	
			la			$a0, nopal				# Load the prompt string
			li			$v0,4					# Load the print string system code
			syscall
			b			end
			
########################################################################
# Description:
# Determine if the string is a palindrome
#
# Arguments:
# 0($sp): The string to test
#
# Returns:
# 4($sp): return value
# 8($sp): $ra
########################################################################
pal:						
			la 			$t1, 0($sp) 			# Get string address
			lw			$t1, 0($t1)				# Load the String address
			
			addi		$sp,$sp, -12			# Allocate Stack Space
			sw			$t1, 0($sp)				# Save the string to $sp[0]			
			sw			$ra, 8($sp)				# Save the current return address	
			jal nChars
			lw			$t2, 4($sp)				# Load the result of nChars into $a1
			lw			$ra, 8($sp)				# Restore the return address
			addi		$sp,$sp, 12				# De-Allocate Stack Space
			
			la 			$t1, 0($sp) 			# Get string address
			lw			$t1, 0($t1)				# Load the String address

			add			$t3,$t1,$t2				# add the nChars to the begining of the string
			addi		$t3,$t3, -1				# add the nChars to the begining of the string
			
pal_loop:
			bge			$t1,$t3,pal_loop_end	# if the two pointers are equal break the loop
			lb			$t4, 0($t1)
			lb			$t5, 0($t3)
			bne			$t4,$t5,pal_not			# if the two letter are not the same skip to end, its not a palendrome
			addi		$t1, 1					# move the front pointer up one character
			addi		$t3, -1					# move the front pointer back one character
			b			pal_loop
pal_not:
			li			$t6, 0
			b			pal_end
pal_loop_end:
			li			$t6, 1
pal_end:
			sw			$t6, 4($sp)
			jr			$ra
########################################################################
# Description:
# Return the number of chars in the string
#
# Arguments:
# 0($sp): The string to test
#
# Returns:
# 4($sp): return value
# 8($sp): $ra
########################################################################
nChars:
			li			$t3,0							# Initilize the counter to 0
			li			$t2,0							# Initilize the loop counter to 0			
			la 			$t4, 0($sp) 					# Get string address
			lw			$t4, 0($t4)						# Load the string address
nChars_loop:

			add			$t5, $t4, $t2					# add the counter to the pointer
			
			lb			$t6,0($t5)						# Load the next char
			bgt			$t6, 126,nChars_End				# If the item is greater then 126 skip inc counter	
			blt			$t6, 32,nChars_End				# If the item is less then 32 skip inc counter
			addi		$t3, 1							# Increment the counter 1	
			addi		$t2, 1							# Increment the Loop counter 1
			bne			$t2, 16, nChars_loop 			# if the counter is greaterthen or equal to 1 then branch to 
nChars_End:
			sw			$t2, 4($sp)
			jr			$ra
			
			
########################################################################
# Description:
# Prints a new Line
#
# Arguments:
# $a0 = not used
#
# Returns:
# $v0 = not used
# $v1 = not used
########################################################################
newLine:
	la			$a0, newln		# Load the new line data item
	li			$v0,4			# Load the print string system code
	syscall	
	jr			$ra					
end: 
			li      $v0, 10							# return control to the OS
			syscall						