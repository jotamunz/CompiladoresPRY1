%include "io.mac" 

.DATA 
out_msg	 db "Input: ",0 

.UDATA 
a	 resd 	 1 
b	 resd 	 1 

.CODE 
	.STARTUP 
	mov EAX, [a] 
	cmp EAX, 0 
	je else0

while0:
	mov EAX, 1 
	cmp EAX, 0 
	je exitWhile0

	jmp while0

exitWhile0:
	jmp exitIf0

else0:
exitIf0:
while1:
	mov EAX, 1 
	cmp EAX, 0 
	je exitWhile1

	mov EAX, 1 
	cmp EAX, 0 
	je else1

	jmp exitIf1

else1:
exitIf1:
	jmp while1

exitWhile1:
j	 resd 	 1 
a	 resd 	 1 
q	 resd 	 1 
	.EXIT 

equals:
    je  isTrue
    mov EAX,0
    ret

greaterEqualThan:
    jge isTrue
    mov EAX,0
    ret

greaterThan:
    jg  isTrue
    mov EAX,0
    ret

lessEqualThan:
    jle isTrue
    mov EAX,0
    ret    

lessThan:
    jl isTrue
    mov EAX,0
    ret  

notEquals:
    jne isTrue
    mov EAX,0
    ret

isTrue:
    mov EAX,1
    ret