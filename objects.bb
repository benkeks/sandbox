
Type TObject
	Field typ
	Field x,y
	Field size,intensity
	Field particles
End Type

Const otBlackHole	= 1
Const otSpawner		= 2
Const otVentilatorl	= 3
Const otVentilatorr = 4

Function Obj_Init()
	Obj_Create.TObject(otSpawner,sizex/4,1,20,89^1.2,1)
	Obj_Create.TObject(otSpawner,sizex/2,1,20,89^1.2,4)
	Obj_Create.TObject(otSpawner,sizex*3/4,1,20,89^1.2,2)
End Function

Function Obj_Update()
	For o.TObject = Each TObject
		Select o\typ
		Case otBlackHole
			If Not main_paused Then
				For i2 = 0 To 1 + o\size
					le = Rand(o\size/2,o\size)
					dir# = Rnd(360)
					s# = Sin(dir)
					c# = Cos(dir)
					x2 = o\x
					y2 = o\y
					For i = 1 To le
						x = o\x + c*i
						y = o\y + s*i
						x2 = o\x + c*(i+1)
						y2 = o\y + s*(i+1)
						If x > 0 And y > 0 And x < sizex-1 And y < sizey-1 Then
							If x2 > 2 And y2 > 2 And x2 < sizex-3 And y2 < sizey-3 And i < le Then
								t2 = PeekByte(bank,x2*sizey-y2)
							Else
								t2 = 0
							EndIf
							PokeByte bank,x*sizey-y,t2
						EndIf
						x2 = x
						y2 = y
					Next
				Next
			EndIf
			Color 200,0,200
			Rect o\x-2,o\y-2,4,4
			If mx > o\x-5 And mx < o\x+5 And my > o\y-5 And my < o\y+5
				Oval o\x-o\size,o\y-o\size,o\size*2,o\size*2,0
				If mrh Then Delete o
			EndIf
		Case otSpawner
			Color 128,128,128
			Rect o\x-o\size,o\y-3,o\size*2,6
			
			If Not main_paused Then
				For i = 1 To o\intensity/2
					PokeByte bank,(o\x+Rand(-o\size,o\size))*sizey+sizey-o\y,o\particles
				Next
			EndIf
			
			If mx > o\x-o\size-1 And mx < o\x+o\size+1 And my > o\y-4 And my < o\y+4
				Color 200,200,200
				Rect o\x-o\size-1,o\y-4,o\size*2+2,8, False
				If mrh Then Delete o
			EndIf
		Case otVentilatorR
			Color 128,128,128
			Rect o\x-5,o\y-o\size,10,o\size*2,0

			If Not main_paused Then
				start = o\x+o\intensity*10
				If start >= sizex Then start = sizex-1
				ende = o\x-o\intensity*10
				If ende < 0 Then ende = 0
				starty = o\y-o\size
				If starty < 0 Then starty = 0
				For y = starty To o\y+o\size
					For x = start To ende Step -1
						If y > sizey Then Exit
						typ = PeekByte(bank,x*sizey-y)
						If typ = 0 Then
							typ2 = PeekByte(bank,x*sizey-sizey-y)
							If typ2 <> 0
								If typ2 <> 3 And typ2 <> 6 And typ2 <> 7 And typ2 <> 11 And typ2 <> 12 Then
									If Rand(0,Abs(x-o\x)/2)<o\intensity/4
										PokeByte bank,x*sizey-y,typ2
										PokeByte bank,x*sizey-sizey-y,typ
									EndIf
								ElseIf x < o\x
									x = ende
								EndIf
							EndIf
						EndIf
					Next
				Next
			EndIf
			If my > o\y-o\size And my < o\y+o\size And mx > o\x-5 And mx < o\x+5
				Rect o\x-o\intensity*4,o\y-o\size,o\intensity*8,o\size*2,0
				If mrh Then Delete o
			EndIf
		Case otVentilatorL
			Color 128,128,128
			Rect o\x-5,o\y-o\size,10,o\size*2,0
			
			If Not main_paused Then
				start = o\x+o\intensity*10
				If start >= sizex Then start = sizex-1
				ende = o\x-o\intensity*10
				If ende < 0 Then ende = 0
				starty = o\y-o\size
				If starty < 0 Then starty = 0
				For y = starty To o\y+o\size
					For x = ende To start
						If y > sizey Then Exit
						typ = PeekByte(bank,x*sizey-y)
						If typ = 0 Then
							typ2 = PeekByte(bank,x*sizey+sizey-y)
							If typ2 <> 0
								If typ2 <> 3 And typ2 <> 6 And typ2 <> 7 And typ2 <> 11 And typ2 <> 12 Then
									If Rand(0,Abs(x-o\x)/2)<o\intensity/4
										PokeByte bank,x*sizey-y,typ2
										PokeByte bank,x*sizey+sizey-y,typ
									EndIf
								ElseIf x > o\x
									x = start
								EndIf
							EndIf
						EndIf
					Next
				Next
			EndIf
			If my > o\y-o\size And my < o\y+o\size And mx > o\x-5 And mx < o\x+5
				Rect o\x-o\intensity*4,o\y-o\size,o\intensity*8,o\size*2,0
				If mrh Then Delete o
			EndIf
		End Select
	Next
End Function

Function Obj_Create.TObject(typ,x,y,size,intensity,particles)
	o.TObject	= New TObject
	o\typ		= typ
	o\x			= x
	o\y			= y
	If o\typ = otBlackHole Then size = size * 2
	o\size		= size
	o\intensity	= Ceil((100-intensity^(1/1.2))/2.0)
	o\particles	= particles
	
	Return o
End Function