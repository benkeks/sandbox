AppTitle "Mr.Keks' Acid Sand Oil Water Box"

Graphics 640,480,16,2
SetBuffer BackBuffer()

Include "sand.bb"
Include "draw.bb"
Include "gui.bb"
Include "objects.bb"


Const sizex = 639
Const sizey = 300

Global ri

;image = CreateImage(sizex,sizey)
Global bank = CreateBank(sizex*sizey+1)

Gui_Init()
Obj_Init()

cmdl$ = Trim(CommandLine())
If Left(cmdl,1) = Chr(34) Then cmdl = Right(cmdl,Len(cmdl)-1)
If Right(cmdl,1) = Chr(34) Then cmdl = Left(cmdl,Len(cmdl)-1)
Sand_Load(cmdl)


Repeat
	Cls
	UpdateSand()
	Obj_Update()
	
	Gui_Update()
	
	Draw_Update()
	
	fps# = (fps*19+1000/(MilliSecs()-ms))/20
	ms = MilliSecs()
	Color 255,255,255
	Text 500,0,fps
	Flip 0
Forever
End