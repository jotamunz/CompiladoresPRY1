%include "io.mac" 

.DATA 
out_msg	 db "Results: ",0 

.UDATA 
x	 resd 	 1 
y	 resd 	 1 
s	 resd 	 1 
x	 resd 	 1 

.CODE 
	.STARTUP 
	mov EAX, [z] 
	add EAX, 2

	mov dword[y], EAX 
	sub EAX,EAX 

	mov dword[s],'a'
	sub EAX,EAX 

a	 resd 	 1 
a	 resd 	 1 
b	 resd 	 1 
c	 resd 	 1 
y	 resd 	 1 
t	 resd 	 1 
	mov EAX, [s] 
	mov dword[t], EAX 
	sub EAX,EAX 

m	 resd 	 1 
	sub EAX,EAX 

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