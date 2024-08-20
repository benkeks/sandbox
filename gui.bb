Dialect "secure"

Type TGadget
	Field x,y,width,height
	Field rx,ry
	Field caption$
	Field typ ; 0 = window, 1 = button
	Field parent.TGadget
	Field handl ; handle of object
End Type

Type TButton
	; extends TGadget
	Field r,g,b
	Field hotkey
	Field OnClick$
End Type

Type TSlider
	; extends TGadget
	Field pos
	Field changing
	Field OnChange$
End Type

Type TFileInput
	; extends TGadget
	Field txt$
	Field OnClick$
	Field OnEscape$
End Type

Const gtWindow		= 0
Const gtButton		= 1
Const gtSlider		= 2
Const gtFileInput 	= 3

; mouse pressed?
Global mh,mrh,mx,my

Function Gui_Init()
	
	SetFont LoadFont("arial",14,True)
	
	Gui_CreateButton.TButton(10,sizey+10,100,18, "Delete"	, 100,100,100, Null ,"Select:Delete")
	Gui_CreateButton.TButton(10,sizey+30,100,18, "Sand"		, 240,240,230, Null ,"Select:Sand")
	Gui_CreateButton.TButton(10,sizey+50,100,18, "Stone"	, $c8,$c8,$c8, Null ,"Select:Stone")
	Gui_CreateButton.TButton(10,sizey+70,100,18, "Oil"		, $da,$a9,$53, Null ,"Select:Oil")
	Gui_CreateButton.TButton(10,sizey+90,100,18, "Fire"		, $ff,$88,$00, Null ,"Select:Fire")
	Gui_CreateButton.TButton(10,sizey+110,100,18, "CO2"		, $60,$40,$30, Null ,"Select:CO2")
	
	Gui_CreateButton.TButton(120,sizey+10,100,18, "Water"	, $30,$65,$ff, Null ,"Select:Water")
	Gui_CreateButton.TButton(120,sizey+30,100,18, "Spout"	, $60,$c0,$ff, Null ,"Select:Spout")
	Gui_CreateButton.TButton(120,sizey+50,100,18, "Steam"	, $00,$74,$a0, Null ,"Select:Steam")
	Gui_CreateButton.TButton(120,sizey+70,100,18, "Acid"	, $ff,$74,$98, Null ,"Select:Acid")
	Gui_CreateButton.TButton(120,sizey+90,100,18, "Gras"	, $40,$ec,$61, Null ,"Select:Gras")
	Gui_CreateButton.TButton(120,sizey+110,100,18, "Cooler"	, $88,$dd,$ff, Null ,"Select:Cooler")
	Gui_CreateButton.TButton(120,sizey+130,100,18, "Heater"	, $ed,$86,$40, Null ,"Select:Heater")
	
	Gui_CreateButton.TButton(230,sizey+10,100,18, "Blackhole"	, $bb,$00,$bb, Null ,"Set:BlackHole")
	Gui_CreateButton.TButton(230,sizey+30,100,18, "Spawner"	, $aa,$aa,$aa, Null ,"Set:Spawner")
	Gui_CreateButton.TButton(230,sizey+50,100,18, "VentilatorL"	, $aa,$aa,$aa, Null ,"Set:VentilatorL")
	Gui_CreateButton.TButton(230,sizey+70,100,18, "VentilatorR"	, $aa,$aa,$aa, Null ,"Set:VentilatorR")
	
	Gui_CreateSlider.TSlider(400,sizey+10,100,5, "BrushSize",  4,	Null,	"Change:BrushWidth")
	Gui_CreateSlider.TSlider(400,sizey+30,100,5, "BrushDensity",  100,	Null,	"Change:BrushDensity")
	
	Gui_CreateButton.TButton(520,sizey+10,100,18, "Pause"	, $aa,$aa,$bb, Null ,"Pause")
	Gui_CreateButton.TButton(520,sizey+40,100,18, "Save"	, $aa,$aa,$bb, Null ,"Save")
	Gui_CreateButton.TButton(520,sizey+60,100,18, "Load"	, $aa,$aa,$bb, Null ,"Load")
	Gui_CreateButton.TButton(520,sizey+90,100,18, "Clear All"	, $aa,$aa,$bb, Null ,"Clear")
	Gui_CreateButton.TButton(520,sizey+110,100,18, "Close"	, $aa,$aa,$bb, Null ,"Close")
End Function

Function Gui_Event(SenderHandle,name$)
	Select name$
	Case ""
	Case "Select:Delete"
		Draw_Selected = sand_EMPTY
	Case "Select:Sand"
		Draw_Selected = sand_SAND
	Case "Select:Stone"
		Draw_Selected = sand_STONE
	Case "Select:Oil"
		Draw_Selected = sand_OIL
	Case "Select:Fire"
		Draw_Selected = sand_FIRE
	Case "Select:CO2"
		Draw_Selected = sand_CO2
	Case "Select:Water"
		Draw_Selected = sand_WATER
	Case "Select:Spout"
		Draw_Selected = sand_SPOUT
	Case "Select:Steam"
		Draw_Selected = sand_STEAM
	Case "Select:Acid"
		Draw_Selected = sand_ACID
	Case "Select:Gras"
		Draw_Selected = sand_GRAS
	Case "Select:Cooler"
		Draw_Selected = sand_COOLER
	Case "Select:Heater"
		Draw_Selected = sand_HEATER
		
	Case "Set:BlackHole"
		Draw_Set	= otBlackHole
	Case "Set:Spawner"
		Draw_Set	= otSpawner
	Case "Set:VentilatorL"
		Draw_Set	= otVentilatorL
	Case "Set:VentilatorR"
		Draw_Set	= otVentilatorR
	
	Case "Change:BrushWidth"
		s.TSlider = Object.TSlider(SenderHandle)
		Draw_BrushSize = 1+s\pos
	Case "Change:BrushDensity"
		s.TSlider = Object.TSlider(SenderHandle)
		Draw_Density = ((101-s\pos)^1.2)
	
	Case "Pause"
		main_paused = Not main_paused
	Case "Save"
		Sand_Save()
	Case "Load"
		FlushKeys
		Gui_CreateFileInput(GraphicsWidth()/2-200,GraphicsHeight()/2-40,400,80, "Please enter file path!", ""	, Null ,"LoadFile","EscapeFile")
	Case "LoadFile"
		f.TFileInput = Object.TFileInput(SenderHandle)
		Sand_Load(f\txt)
	Case "EscapeFile"

	Case "Clear"
		Sand_Clear()
		Obj_Init()
	Case "Close"
		End
	
	End Select
End Function


Function Gui_Update()
	Color 50,50,50
	Rect 0,sizey,GraphicsWidth(),GraphicsHeight()-sizey
	mx = MouseX()
	my = MouseY()
	mh = MouseHit(1)
	mrh = MouseHit(2)
	key = GetKey()
	For g.TGadget = Each TGadget
		g\rx = g\x
		g\ry = g\y
		
		If g\parent <> Null Then
			g\rx = g\x + g\parent\rx
			g\ry = g\y + g\parent\ry
		EndIf
		
		Select g\typ
		Case gtWindow
			
		Case gtButton
			b.TButton = Object.TButton(g\Handl)
			If mx>g\rx And mx<g\rx+g\width And my>g\ry And my<g\ry+g\height Then
				Color b\r,b\g,b\b
				Rect g\rx,g\ry,g\width,g\height,True
				Color 255,255,255
				Rect g\rx,g\ry,g\width,g\height,False
				Text g\rx+g\width/2, g\ry+g\height/2, g\caption, True, True
				If mh Then Gui_Event(Handle(b),b\OnClick)
			Else
				Color b\r/2,b\g/2,b\b/2
				Rect g\rx,g\ry,g\width,g\height,True
				Color b\r,b\g,b\b
				Rect g\rx,g\ry,g\width,g\height,False
				Color 255,255,255
				Text g\rx+g\width/2, g\ry+g\height/2, g\caption, True, True
			EndIf
		Case gtSlider
			s.TSlider = Object.TSlider(g\Handl)
			Color 255,255,255
			Text g\rx,g\ry, g\caption
			If mx>g\rx And mx<g\rx+g\width And my>g\ry+10 And my<g\ry+g\height+18 Then
				Color 150,150,150
				Rect g\rx,g\ry+14,g\width,g\height,True
				Color 255,255,255
				Rect g\rx,g\ry+14,g\width,g\height,False
				If mh Then
					s\changing = 1
					s\pos = (mx-g\rx)*100/g\width
					Gui_Event(Handle(s),s\OnChange)
					If s\pos < 0 Then s\pos = 0
					If s\pos > 100 Then s\pos = 100
				ElseIf s\changing = 1
					s\pos = (mx-g\rx)*100/g\width
					If s\pos < 0 Then s\pos = 0
					If s\pos > 100 Then s\pos = 100
					If MouseDown(1)=0 Then s\changing = 0
					Gui_Event(Handle(s),s\OnChange)
				EndIf
			Else				
				Color 100,100,100
				Rect g\rx,g\ry+14,g\width,g\height,True
				Color 230,230,230
				Rect g\rx,g\ry+14,g\width,g\height,False
				
				If s\changing = 1
					s\pos = (mx-g\rx)*100/g\width
					If s\pos < 0 Then s\pos = 0
					If s\pos > 100 Then s\pos = 100
					If MouseDown(1) =0 Then s\changing = 0
					Gui_Event(Handle(s),s\OnChange)
				EndIf
			EndIf
			Color 255,255,255
			Rect g\rx+s\pos*g\width/100-1,g\ry+12,3,g\height+4
		Case gtFileInput
			f.TFileInput = Object.TFileInput(g\Handl)
			If f = Null Then 
				Delete g
			Else
				Color 150,150,150
				Rect g\rx,g\ry,g\width,g\height,True
				Color 255,255,255
				Rect g\rx,g\ry,g\width,g\height,False
				
				Text g\rx+g\width/2,g\ry+5,g\caption,1
				
				Rect g\rx+10,g\ry+30,g\width-20,20,0
				txt$ = f\txt
				While StringWidth(txt$) > g\width-30
					txt = Right(txt,Len(txt)-2)
				Wend
				Text g\rx+15,g\ry+34,txt
				
				main_paused = True; pause everything while dialogue is open
				Select key
				Case 0
				Case 13; enter
					Gui_Event(Handle(f),f\OnClick)
					Delete f
					main_paused = False
				Case 8 ; backspace
					If Len(f\txt)>0 Then f\txt = Left(f\txt,Len(f\txt)-1)
				Case 27; escaepe
					Gui_Event(Handle(f),f\OnEscape)
					Delete f
					main_paused = False
				Default
					f\txt = f\txt + Chr(key)
				End Select
			EndIf
		End Select
	Next
	
	Color 230,230,230
	Text GraphicsWidth() * .5, GraphicsHeight() - 10, "benkeks.itch.io/sandbox  |  mrkeks.net", True, True
End Function

Function Gui_NewGadget.TGadget(x,y,width,height,caption$,typ,handl,parent.TGadget)
	g.TGadGet	= New TGadGet
	g\x			= x
	g\y			= y
	g\width		= width
	g\height	= height
	g\caption	= caption
	g\parent	= parent
	g\typ		= typ
	g\handl		= handl
	Return g
End Function

Function Gui_CreateButton.TButton(x,y,width,height, caption$, r,g,bl, parent.TGadget ,onclick$,hotkey=0)
	b.TButton	= New TButton
	Gui_NewGadget(x,y,width,height,caption$,gtButton,Handle(b),parent.TGadget)
	
	b\r			= r
	b\g			= g
	b\b			= bl
	
	b\onclick	= onclick
	b\hotkey	= hotkey
	
	Return b
End Function

Function Gui_CreateSlider.TSlider(x,y,width,height, caption$, pos, parent.TGadget ,OnChange$)
	s.TSlider	= New TSlider
	Gui_NewGadget(x,y,width,height,caption$,gtSlider,Handle(s),parent.TGadget)
	
	s\pos		= pos
	
	s\onChange	= onChange
	
	Return s
End Function

Function Gui_CreateFileInput.TFileInput(x,y,width,height, caption$, txt$, parent.TGadget ,onclick$, onescape$)
	f.TFileInput= New TFileInput
	Gui_NewGadget(x,y,width,height,caption$,gtFileInput,Handle(f),parent.TGadget)
	
	f\onclick	= onclick
	f\onescape	= onescape
	f\txt		= txt$
	
	Return f
End Function