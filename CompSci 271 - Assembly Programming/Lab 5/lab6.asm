########################################################################
# Nick Bolles
# COMPSCI 271 Lab 6
# April 6-7, 2015
########################################################################
# Main program description:
# Search a predefined array of numbers for a specific value that is
# requested by the user.
#
# Arguments: None.
#
# Returns: Nothing.
########################################################################

        .data
X:      .byte   1, 27, 92, 46, 72, 8, 13, 93, 65, 112
N:      .word   10
endl:   .asciiz "\n"
        # Fill in additional .asciiz directives for I/O messages here
numPrompt:   .asciiz "Enter a number to search for: "
elNumPrompt:   .asciiz "This number is in Element # "
notInPrompt:   .asciiz "This number is not in the array!"

        .text
main:
        # 1. Load address of X into a register (e.g., $s0)
			la	$s0, X
		# 2. Load value of N into a register (e.g., $s1)
			lw	$s1, N
		# 3. Prompt user to enter a number to search for, then:
			# Ask the user what number they would like to find
			la			$a0, numPrompt			# Load the prompt string
			li			$v0,4					# Load the print string system code
			syscall		
		# 4. Get the number to find from the keyboard
			# Get the users answer
			li			$v0, 5					# Load the get integer system code
			syscall
			move		$s5,$v0
			jal newLine			
		# 5. Prepare the stack frame.
		#    * How many input parameters do you need to store?
		#    * How many values are being returned?
		#    * Do you want to store the return address?
		#    Use answers to these questions to make the stack frame. 
			addi		$sp,$sp, 12			# Allocate Stack Space
			li			$t5, -1	
			mult		$s1, $t5				# Find the offset of the element
			mflo		$s2						# Load the result of the offset into $s6
			sw			$s0, 0($sp)				# Save the address of the array in $sp[0]
			sw			$s1, 4($sp)				# Save the number of items in the array in $sp[1]
			sw			$s5, 8($sp)				# Save the number to look for in $sp[2]			
			
        # 6. Call the Search function
			jal 		search
		# 7. Copy returned value from the Search function into a 
		#    register, then deallocate the stack frame's space
			lw 			$s4, 12($sp)				# Load the return value from $sp[0]
			add			$sp,$sp, -12			# De-allocate the Stack Space
		# 8. If number was found in element N, display:
		#    "This number is in element N"
		#    Otherwise: "This number is not in the array" 
			bltz		$s4, printNotInPompt	# If the result is less then 0 (-1) then print
			
			la			$a0, elNumPrompt		# Load the prompt string
			li			$v0,4					# Load the print string system code
			syscall		
			move		$a0, $s4				# Load the integer
			li			$v0,1					# Load the print int system code
			syscall		
			b 			finish
printNotInPompt:
			la			$a0, notInPrompt		# Load the prompt string
			li			$v0,4					# Load the print string system code
			syscall		
        # 9. Return control to the operating system		
finish: li      $v0, 10
        syscall

########################################################################
# Description:
# Sequentially (linearly) search the given array for the relative
# location (index) of a given value. If the value is found, the array
# index (between 0 and N-1) of the value is returned. Otherwise, this
# function returns -1.
#
# Arguments:
# 0($sp): starting address of array
# 4($sp): number of array elements
# 8($sp): value to search for
#
# Returns:
# 12($sp): index of element where value is found, or -1 if not found
########################################################################
        .text	
search:	
			lw 			$t1, 0($sp) 			# Get start address of X
			lw 			$t2, 4($sp) 			# Get N
			lw 			$t3, 8($sp)				# Get Value To Search For
			li 			$t4, 0 					# init Loop Counter
search_loop:
			ble			$t2, $t4,search_Not_Found# If N <= I then we are done
			add			$t7, $t4, $t1			# Get the address of $sp[i]
			lb			$t6, 0($t7)				# Load the byte from $sp[i]
			beq			$t3, $t6,search_Found	# If this item is equal to the value we 
												# are searching for branch to search_Found to return it
			addi		$t4, 1					# increment the loop counter
			b			search_loop				# loop
search_Found:
			sw			$t4, 12($sp)			# Store the loop counter into $sp[3]
			b			search_Fin
search_Not_Found:
			li			$t5, -1
			sw			$t5, 12($sp)
search_Fin:
			jr	$ra
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
	la			$a0, endl		# Load the new line data item
	li			$v0,4			# Load the print string system code
	syscall	
	jr			$ra