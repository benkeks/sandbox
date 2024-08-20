Dialect "secure"

Global Draw_lastx, Draw_lasty

Global Draw_BrushSize	= 5
Global Draw_Selected	= 3
Global Draw_Set			= 0
Global Draw_Density 	= 1

Function Draw_Update()
	If my < sizey And Draw_Selected >= 0 Then
		Select Draw_Selected
		Case 0 ; nichts
			col = 0
		Case 1 ; sand
			col = $ccccaa
		Case 2 ; wasser
			col = $0000ff
		Case 3 ; stein
			col = $888888
		Case 4 ; öl
			col = $aa9933
		Case 5 ; feuer
			col = $ff8800
		Case 6 ; gras
			col = $00cc11
		Case 7 ; wolke
			col = $40a0ff
		Case 8 ; co2 (gas)
			col = $604030
		Case 9 ; wasserdampf
			col = $0030a0
		Case 10; säure
			col = $ff0033
		Case 11; herd
			col = $ed8640
		Case 12; kühler
			col = $88ddff
		End Select
		Color 0,0,col
		Oval mx-draw_brushsize/2,my-draw_brushsize/2,draw_brushsize,draw_brushsize,0
		
		If mh And Draw_Set > 0 
			Obj_Create(Draw_Set,mx,my,Draw_BrushSize,Draw_Density,Draw_Selected)
			Draw_Set	= -1
		ElseIf Draw_Set = -1
			If MouseDown(1) = 0 Then Draw_Set = 0
		ElseIf MouseDown(1) And draw_brushsize>0
			dist# = Sqr((Draw_lastx-mx)^2 + (Draw_lasty-my)^2)
			If dist <= Draw_brushsize/2
				Draw_Circle(mx,my,Draw_BrushSize,Draw_Selected)
			Else
				For i = 0 To dist/draw_brushsize*2
					Draw_Circle(Draw_Lastx+(mx-Draw_lastx)*i*Draw_BrushSize/dist/2,Draw_Lasty+(my-Draw_lasty)*i*Draw_BrushSize/dist/2,Draw_BrushSize,Draw_Selected)
				Next
			EndIf
		EndIf
	EndIf
	Draw_lastx = mx
	Draw_Lasty = my
End Function


Function Draw_Circle(xCenter,yCenter,radius,byte)
	x=0
	y=radius/2
	;SetColor $FF,$88,$00
	Draw_Rect bank,xCenter-y,yCenter+x,y Shl 1,1,byte
	;SetColor $FF,$FF,$FF
	Draw_Plot bank,xCenter+x,yCenter+y,byte
	Draw_Plot bank,xCenter-x,yCenter+y,byte
	Draw_Plot bank,xCenter+x,yCenter-y,byte
	Draw_Plot bank,xCenter-x,yCenter-y,byte
	Draw_Plot bank,xCenter+y,yCenter+x,byte
	Draw_Plot bank,xCenter-y,yCenter+x,byte
	Draw_Plot bank,xCenter+y,yCenter-x,byte
	Draw_Plot bank,xCenter-y,yCenter-x,byte
	p=1-radius
	While x<y-1
		prevy=y
		If p<0
			x=x+1
		Else
			x=x+1
			y=y-1
		EndIf
		If p<0
			p=p+(x Shl 1)+1
		Else
			p=p+((x-y) Shl 1)+1
		EndIf
		If y<prevy And x<y
			;SetColor $FF,$88,$00
			Draw_Rect bank,xCenter-x,yCenter+y,x Shl 1,1,byte
			Draw_Rect bank,xCenter-x,yCenter-y,x Shl 1,1,byte
			;SetColor $FF,$FF,$FF
			Draw_Plot bank,xCenter+x,yCenter+y,byte
			Draw_Plot bank,xCenter-x,yCenter+y,byte
			Draw_Plot bank,xCenter+x,yCenter-y,byte
			Draw_Plot bank,xCenter-x,yCenter-y,byte
		EndIf
		;SetColor $FF,$88,$00
		Draw_Rect bank,xCenter-y,yCenter+x,y Shl 1,1,byte
		Draw_Rect bank,xCenter-y,yCenter-x,y Shl 1,1,byte
		;SetColor $FF,$FF,$FF
		Draw_Plot bank,xCenter+y,yCenter+x,byte
		Draw_Plot bank,xCenter-y,yCenter+x,byte
		Draw_Plot bank,xCenter+y,yCenter-x,byte
		Draw_Plot bank,xCenter-y,yCenter-x,byte
	Wend
End Function

Function Draw_Plot(bank,x,y,byte)
	pos = x*sizey+sizey-y 
	If x > 0 And y > 0 And x < sizex And y < sizey And Rand(1,draw_density)=1 Then PokeByte bank,pos,byte
End Function

Function Draw_Rect(bank,xs,ys,width,height,byte)
	For x = xs To xs+width
		For y = ys To ys+height
			pos = x*sizey+sizey-y 
			If x > 0 And y > 0 And x < sizex And y < sizey And Rand(1,draw_density)=1 Then PokeByte bank,pos,byte
		Next
	Next
End Function