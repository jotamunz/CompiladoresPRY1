%include "io.mac" 

.DATA 
out_msg	 db "Input: ",0 

.UDATA 
x	 resd 	 1 
y	 resd 	 1 
a	 resd 	 1 
b	 resd 	 1 
c	 resd 	 1 

.CODE 
	.STARTUP 
	mov EAX, [x] 
	cmp EAX, 0 
	je else0

	mov EAX, [x] 
	sub EAX, 2

	mov dword[x], EAX 
	xor EAX,EAX 

	mov dword[y],-2
	xor EAX,EAX 

	mov EAX, [y] 
	mov EBX, [x] 
	add EAX,EBX

	mov dword[b], EAX 
	xor EAX,EAX 

	jmp exitIf0

else0:
	mov EAX, [x] 
	mov dword[x], EAX 
	xor EAX,EAX 

exitIf0:
	mov EAX, 'c' 
	cmp EAX, 0 
	je else1

	jmp exitIf1

else1:
	mov EAX, [x] 
	inc dword[x] 

	inc dword[x] 
	mov EAX, [x] 

exitIf1:
while0:
	mov EAX, [x] 
	cmp EAX, 0 
	je exitWhile0

	mov EAX, [x] 
	add EAX, 1

	mov dword[y], EAX 
	xor EAX,EAX 

	mov EAX, [x] 
	inc dword[x] 

	jmp while0

exitWhile0:
	xor EAX,EAX 
	PutStr out_msg 
	GetLInt EAX 
	mov dword[x], EAX 

	mov EAX, [x] 
	PutLInt EAX 
	nwln 
	xor EAX,EAX 

	mov EAX, [y] 
	PutLInt EAX 
	nwln 
	xor EAX,EAX 

	mov EAX, [a] 
	PutLInt EAX 
	nwln 
	xor EAX,EAX 

	mov EAX, [b] 
	PutLInt EAX 
	nwln 
	xor EAX,EAX 

	mov EAX, [c] 
	PutLInt EAX 
	nwln 
	xor EAX,EAX 

	mov EAX, 1 
	PutLInt EAX 
	nwln 
	xor EAX,EAX 

	mov EAX, 'c' 
	PutCh AL 
	nwln 
	xor EAX,EAX 

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