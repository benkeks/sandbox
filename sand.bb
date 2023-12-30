Const sand_EMPTY	= 0
Const sand_SAND		= 1
Const sand_WATER	= 2
Const sand_STONE	= 3
Const sand_OIL		= 4
Const sand_FIRE		= 5
Const sand_GRAS		= 6
Const sand_SPOUT	= 7
Const sand_CO2		= 8
Const sand_STEAM	= 9
Const sand_ACID		= 10
Const sand_HEATER	= 11
Const sand_COOLER	= 12

Global sand_color[32]
Global sand_color_light[32]
Global sand_color_dark[32]

sand_color[sand_EMPTY]		= 0
sand_color[sand_SAND]		= $ccccaa
sand_color[sand_WATER]		= $0000ff
sand_color[sand_STONE]		= $888888
sand_color[sand_OIL]		= $aa9933
sand_color[sand_FIRE]		= $ff8800
sand_color[sand_GRAS]		= $00cc11
sand_color[sand_SPOUT]		= $40a0ff
sand_color[sand_CO2]		= $604030
sand_color[sand_STEAM]		= $0030a0
sand_color[sand_ACID]		= $ff0033
sand_color[sand_HEATER]		= $ed8640
sand_color[sand_COOLER]		= $88ddff

sand_color_light[sand_EMPTY]	= 0
sand_color_light[sand_SAND]		= $ffffff
sand_color_light[sand_WATER]	= $3065ff
sand_color_light[sand_STONE]	= $c8c8c8
sand_color_light[sand_OIL]		= $daa953
sand_color_light[sand_FIRE]		= $ff8800
sand_color_light[sand_GRAS]		= $40ec61
sand_color_light[sand_SPOUT]	= $60c0ff
sand_color_light[sand_CO2]		= $604030
sand_color_light[sand_STEAM]	= $0030a0
sand_color_light[sand_ACID]		= $ff7498
sand_color_light[sand_HEATER]	= $ed8640
sand_color_light[sand_COOLER]	= $88ddff

sand_color_dark[sand_EMPTY]		= 0
sand_color_dark[sand_SAND]		= $bbbbbb
sand_color_dark[sand_WATER]		= $0000bb
sand_color_dark[sand_STONE]		= $555555
sand_color_dark[sand_OIL]		= $774400
sand_color_dark[sand_FIRE]		= $ff8800
sand_color_dark[sand_GRAS]		= $009900
sand_color_dark[sand_SPOUT]		= $1188aa
sand_color_dark[sand_CO2]		= $604030
sand_color_dark[sand_STEAM]		= $0030a0
sand_color_dark[sand_ACID]		= $aa0000
sand_color_dark[sand_HEATER]	= $ed8640
sand_color_dark[sand_COOLER]	= $88ddff

Function UpdateSand()
	LockBuffer BackBuffer()
	
	For i = 1 To 1
		ri = 1-ri
		x = (sizex-1)*(ri=1)
		Ende = (sizex-1)*(ri=0)
		While (x < ende And ri = 0) Or (x > ende And ri = 1)
			If ri = 1 Then x = x - 1 Else x = x + 1
			typo = sand_EMPTY
			For y = 0 To sizey-1
				off = x*sizey+y
				typ = PeekByte(bank,off)
				showtyp = typ
				showtyp2 = PeekByte(bank,off+1)

				Select typ
				Case sand_EMPTY,3
					
				Case sand_SAND, sand_WATER, sand_OIL, sand_ACID
					If typ = sand_ACID And Rand(10)=5; corrode
						r = Rand(1,10)
						Select r
						Case 1
							If x > 0 And y < sizey-1
								typ2 = PeekByte(bank,x*sizey-sizey+y+1)
								If typ2 = sand_WATER
									If Rand(0,400) Then PokeByte bank,x*sizey-sizey+y+1,typ
									PokeByte bank,off,typ2
									typ = typ2
								ElseIf typ2 <> sand_EMPTY And typ2 < sand_ACID And typ2 <> sand_CO2 And Rand(0,1)
									If Rand(0,1) Then PokeByte bank,off-sizey+1,typ
									PokeByte bank,off,sand_CO2
									typ = sand_CO2
								EndIf
							EndIf
						Case 2
							If y < sizey-1
								typ2 = PeekByte(bank,off+1)
								If typ2 = sand_WATER
									If Rand(0,400) Then PokeByte bank,off+1,typ
									PokeByte bank,off,typ2
									typ = typ2
								ElseIf typ2 <> sand_EMPTY And typ2 < sand_ACID And typ2 <> sand_CO2 And Rand(0,1)
									If Rand(0,1) Then PokeByte bank,off+1,typ
									PokeByte bank,off,sand_CO2
									typ = sand_CO2
								EndIf
							EndIf
						Case 3
							If x < sizex-1 And y < sizey
								typ2 = PeekByte(bank,x*sizey+sizey+y+1)
								If typ2 = sand_WATER
									If Rand(0,400) Then PokeByte bank,x*sizey+sizey+y+1,typ
									PokeByte bank,off,typ2
									typ = typ2
								ElseIf typ2 <> sand_EMPTY And typ2 < sand_ACID And typ2 <> sand_CO2 And Rand(0,1)
									If Rand(0,1) Then PokeByte bank,off+sizey+1,typ
									PokeByte bank,off,sand_CO2
									typ = sand_CO2
								EndIf
							EndIf
						Case 4
							If x > 0
								typ2 = PeekByte(bank,x*sizey-sizey+y)
								If typ2 = sand_WATER
									If Rand(0,400) Then PokeByte bank,x*sizey-sizey+y,typ
									PokeByte bank,off,typ2
									typ = typ2
								ElseIf typ2 <> sand_EMPTY And typ2 < sand_ACID And typ2 <> sand_CO2 And Rand(0,1)
									If Rand(0,1) Then PokeByte bank,off-sizey,typ
									PokeByte bank,off,sand_CO2
									typ = sand_CO2
								EndIf
							EndIf
						Case 5
							If x < sizex-1
								typ2 = PeekByte(bank,x*sizey+sizey+y)
								If typ2 = sand_WATER
									If Rand(0,400) Then PokeByte bank,x*sizey+sizey+y,typ
									PokeByte bank,off,typ2
									typ = typ2
								ElseIf typ2 <> sand_EMPTY And typ2 < sand_ACID And typ2 <> sand_CO2 And Rand(0,1)
									If Rand(0,1) Then PokeByte bank,off+sizey,typ
									PokeByte bank,off,sand_CO2
									typ = sand_CO2
								EndIf
							EndIf
						Case 6
							If x > 0 And y >0
								typ2 = PeekByte(bank,x*sizey-sizey+y-1)
								If typ2 = sand_WATER
									If Rand(0,400) Then PokeByte bank,x*sizey-sizey+y-1,typ
									PokeByte bank,off,typ2
									typ = typ2
								ElseIf typ2 <> sand_EMPTY And typ2 < sand_ACID And typ2 <> sand_CO2 And Rand(0,1)
									If Rand(0,1) Then PokeByte bank,off-sizey-1,typ
									PokeByte bank,off,sand_CO2
									typ = sand_CO2
								EndIf
							EndIf
						Case 7
							If y > 0
								typ2 = PeekByte(bank,off-1)
								If typ2 = sand_WATER
									If Rand(0,400) Then PokeByte bank,off-1,typ
									PokeByte bank,off,typ2
									typ = typ2
								ElseIf typ2 <> sand_EMPTY And typ2 < sand_ACID And typ2 <> sand_CO2 And Rand(0,1)
									If Rand(0,1) Then PokeByte bank,off-1,typ
									PokeByte bank,off,sand_CO2
									typ = sand_CO2
								EndIf
							EndIf
						Case 8
							If x < sizex-1 And y > 0
								typ2 = PeekByte(bank,x*sizey+sizey+y-1)
								If typ2 = sand_WATER
									If Rand(0,400) Then PokeByte bank,x*sizey+sizey+y-1,typ
									PokeByte bank,off,typ2
									typ = typ2
								ElseIf typ2 <> sand_EMPTY And typ2 < sand_ACID And typ2 <> sand_CO2 And Rand(0,1)
									If Rand(0,1) Then PokeByte bank,off+sizey-1,typ
									PokeByte bank,off,sand_CO2
									typ = sand_CO2
								EndIf
							EndIf
						End Select
					EndIf
					If typo = sand_EMPTY Or typo = sand_CO2 Or typo = sand_STEAM Or (typ = sand_SAND And (typo = sand_WATER Or typo = sand_OIL)) Or (typ = sand_WATER And typo = sand_OIL) Then
						If typo = sand_EMPTY Or Rand(0,1)
							If Rand(10) > 1
								PokeByte bank,off,typo
								If y > 0 Then PokeByte bank,off-1,typ
								typ = typo
							EndIf
						EndIf
					ElseIf y > 0
						If typ = sand_WATER Or typ = sand_OIL
							If x < 1
								typ3 = typ
							Else
								typ3 = PeekByte(bank,x*sizey-sizey+y)
							EndIf
							
							If x > sizex-2
								typ2 = typ
							Else
								typ2 = PeekByte(bank,x*sizey+sizey+y)
							EndIf
							
							If typ2 = typ And (typ3 = sand_EMPTY Or (typ3 = sand_OIL And typ = sand_WATER) Or ((typ3 = sand_CO2 Or typ3 = sand_STEAM) And Rand(0,1)))
								PokeByte bank,off,typ3
								PokeByte bank,x*sizey-sizey+y,typ
								typ = typ3
							ElseIf typ3 = typ And (typ2 = sand_EMPTY Or (typ2 = sand_OIL And typ = sand_WATER) Or ((typ2 = sand_CO2 Or typ2 = sand_STEAM) And Rand(0,1)))
								PokeByte bank,off,typ2
								PokeByte bank,x*sizey+sizey+y,typ
								typ = typ2
							EndIf
						EndIf
						If typ = sand_WATER Or typ = sand_OIL Or typ = sand_ACID
							If x < 1
								typ3 = typ
							Else
								typ3 = PeekByte(bank,x*sizey-sizey+y)
							EndIf
							
							If x > sizex-2
								typ2 = typ
							Else
								typ2 = PeekByte(bank,x*sizey+sizey+y)
							EndIf
						
							
							If typ2 = typ And (typ3 = sand_EMPTY Or (typ3 = sand_OIL And typ = sand_WATER))
								PokeByte bank,off,typ3
								PokeByte bank,x*sizey-sizey+y,typ
								typ = typ3
							ElseIf typ3 = typ And (typ2 = sand_EMPTY Or (typ2 = sand_OIL And typ = sand_WATER))
								PokeByte bank,off,typ2
								PokeByte bank,x*sizey+sizey+y,typ
								typ = typ2
							EndIf
							
							If x < 1
								typ3 = typ
							Else
								typ3 = PeekByte(bank,x*sizey-sizey+y)
							EndIf
							
							If x > sizex-2
								typ2 = typ
							Else
								typ2 = PeekByte(bank,x*sizey+sizey+y)
							EndIf
						
							
							If typ2 = typ And (typ3 = sand_EMPTY Or (typ3 = sand_OIL And typ = sand_WATER))
								PokeByte bank,off,typ3
								PokeByte bank,x*sizey-sizey+y,typ
								typ = typ3
							ElseIf typ3 = typ And (typ2 = sand_EMPTY Or (typ2 = sand_OIL And typ = sand_WATER))
								PokeByte bank,off,typ2
								PokeByte bank,x*sizey+sizey+y,typ
								typ = typ2
							EndIf
						EndIf
						
						If Rand(1,2) = 1 And x < sizex-2
							typ2 = PeekByte(bank,x*sizey+sizey+y-1)
							If typ2 = sand_EMPTY Or (typ2 = sand_WATER And typ = sand_SAND And Rand(0,1)) Or ((typ2 = sand_OIL Or typ2 = sand_CO2 Or typ2 = sand_STEAM) And Rand(0,1))
								PokeByte bank,off,typ2
								PokeByte bank,x*sizey+sizey+y-1,typ
								typ = typ2
							ElseIf Rand(0,1) And x < sizex-3
								typ2 = PeekByte(bank,x*sizey+sizey*2+y-1)
								If typ2 = sand_EMPTY Or (typ2 = sand_WATER And typ = sand_SAND And Rand(0,1)) Or ((typ2 = sand_OIL Or typ2 = sand_CO2 Or typ2 = sand_STEAM) And Rand(0,1))
									PokeByte bank,off,typ2
									PokeByte bank,x*sizey+sizey*2+y-1,typ
									typ = typ2
								EndIf
							EndIf
						ElseIf x > 1
							typ2 = PeekByte(bank,x*sizey-sizey+y-1)
							If typ2 = sand_EMPTY Or (typ2 = sand_WATER And typ = sand_SAND And Rand(0,1)) Or ((typ2 = sand_OIL Or typ2 = sand_CO2 Or typ2 = sand_STEAM) And Rand(0,1))
								PokeByte bank,off,typ2
								PokeByte bank,x*sizey-sizey+y-1,typ
								typ = typ2
							ElseIf Rand(0,1) And x > 2
								typ2 = PeekByte(bank,x*sizey-sizey*2+y-1)
								If typ2 = sand_EMPTY Or (typ2 = sand_WATER And typ = sand_SAND And Rand(0,1)) Or ((typ2 = sand_OIL Or typ2 = sand_CO2 Or typ2 = sand_STEAM) And Rand(0,1))
									PokeByte bank,off,typ2
									PokeByte bank,x*sizey-sizey*2+y-1,typ
									typ = typ2
								EndIf
							EndIf
						EndIf
					EndIf
				Case sand_FIRE ; feuer
					If Rand(50)=1
						PokeByte bank,off,8
						If PeekByte(bank,off+1) = sand_WATER Then PokeByte bank,off,9
						typ = sand_CO2
					ElseIf Rand(0,1) And y < sizey-2
						typ2 = PeekByte(bank,off+1)
						Select typ2
						Case sand_EMPTY
							If Rand(2)=2
								PokeByte bank,off,typ2
								PokeByte bank,off+1,typ
								typ = typ2
							EndIf
						Case sand_SAND,sand_STONE,sand_HEATER
							If Rand(10)=2
								PokeByte bank,off,0
								typ = sand_EMPTY
							EndIf
						Case sand_WATER,sand_COOLER
							PokeByte bank,off,9
							typ = sand_STEAM
						Case sand_OIL,sand_GRAS
							If Rand(0,1)
								PokeByte bank,off+1,typ
								typ = typ
							EndIf
						End Select
					ElseIf Rand(0,1) And y > 1
						If Rand(0,1) And x < sizex-2
							typ2 = PeekByte(bank,x*sizey+sizey+y)
							Select typ2
							Case sand_EMPTY
								If Rand(20)=2
									PokeByte bank,off,typ2
									PokeByte bank,x*sizey+sizey+y-1,typ
									typ = typ2
								EndIf
							Case sand_SAND,sand_STONE,sand_HEATER
								If Rand(10)=2
									PokeByte bank,off,0
									typ = sand_EMPTY
								EndIf
							Case sand_WATER,sand_COOLER
								If Rand(2)=1
									PokeByte bank,off,0
									typ = sand_EMPTY
								EndIf
							Case sand_OIL,sand_GRAS
								If Rand(0,1)
									PokeByte bank,x*sizey+sizey+y-1,typ
									typ = typ
								EndIf
							End Select
						ElseIf x > 1 And Rand(0,1)
							typ2 = PeekByte(bank,x*sizey-sizey+y-1)
							Select typ2
							Case sand_EMPTY
								If Rand(20=2)
									PokeByte bank,off,typ2
									PokeByte bank,x*sizey-sizey+y-1,typ
									typ = typ2
								EndIf
							Case sand_SAND,sand_STONE,sand_HEATER
								If Rand(10)=2
									PokeByte bank,off,0
									typ = sand_EMPTY
								EndIf
							Case sand_WATER,sand_COOLER
								If Rand(2)=1
									PokeByte bank,off,0
									typ = sand_EMPTY
								EndIf
							Case sand_OIL,sand_GRAS
								If Rand(0,1)
									PokeByte bank,x*sizey-sizey+y-1,typ
									typ = typ
								EndIf
							End Select
						Else
							typ2 = PeekByte(bank,off+1)
							Select typ2
							Case sand_EMPTY
								If Rand(10)=5
									PokeByte bank,off,typ2
									PokeByte bank,off+1,typ
									typ = typ2
								EndIf
							Case sand_SAND,sand_STONE,sand_HEATER
								If Rand(10)=2
									PokeByte bank,off,0
									typ = sand_EMPTY
								EndIf
							Case sand_WATER,sand_COOLER
								PokeByte bank,off,0
								typ = sand_EMPTY
							Case sand_OIL,sand_GRAS
								If Rand(0,1)
									PokeByte bank,off+1,typ
									typ = typ
								EndIf
							End Select
						EndIf
					Else
						If Rand(0,1) And x < sizex-2
							typ2 = PeekByte(bank,x*sizey+sizey+y)
							Select typ2
							Case sand_EMPTY
								PokeByte bank,off,typ2
								PokeByte bank,x*sizey+sizey+y,typ
								typ = typ2
							Case sand_SAND,sand_STONE,sand_HEATER
								If Rand(10)=2
									PokeByte bank,off,0
									typ = sand_EMPTY
								EndIf
							Case sand_WATER,sand_COOLER
								If Rand(2-(typ2=sand_COOLER))=1
									PokeByte bank,off,0
									typ = sand_EMPTY
								EndIf
							Case sand_OIL,sand_GRAS
								If Rand(0,1)
									PokeByte bank,x*sizey+sizey+y,typ
									typ = typ
								EndIf
							End Select
						ElseIf x > 1
							typ2 = PeekByte(bank,x*sizey-sizey+y)
							Select typ2
							Case sand_EMPTY
								PokeByte bank,off,typ2
								PokeByte bank,x*sizey-sizey+y,typ
								typ = typ2
							Case sand_SAND,sand_STONE,sand_HEATER
								If Rand(10)=2
									PokeByte bank,off,0
									typ = sand_EMPTY
								EndIf
							Case sand_WATER,sand_COOLER
								If Rand(2-(typ2=sand_COOLER))=1
									PokeByte bank,off,0
									typ = sand_EMPTY
								EndIf
							Case sand_OIL,sand_GRAS
								If Rand(0,1)
									PokeByte bank,x*sizey-sizey+y,typ
									typ = typ
								EndIf
							End Select
						EndIf
					EndIf
				Case sand_GRAS
					r = Rand(1,80)
					Select r
					Case 1
						If x > 1 And y < sizey-2
							typ2 = PeekByte(bank,x*sizey-sizey+y+1)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey-sizey+y+1, (1 = Rand(1)) * sand_GRAS
							ElseIf typ2 = sand_CO2 And Rand(1)
								PokeByte bank,x*sizey-sizey+y+1, (2 = Rand(2)) * sand_GRAS
							EndIf
						EndIf
					Case 2
						If y < sizey-2
							typ2 = PeekByte(bank,off+1)
							If typ2 = sand_WATER
								PokeByte bank,off+1, (1 = Rand(1)) * sand_GRAS
							ElseIf typ2 = sand_CO2 And Rand(1)
								PokeByte bank,x*sizey+y-1, (2 = Rand(2)) * sand_GRAS
							EndIf
						EndIf
					Case 3
						If x < sizex-2 And y < sizey
							typ2 = PeekByte(bank,x*sizey+sizey+y+1)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey+sizey+y+1, (1 = Rand(1)) * sand_GRAS
							ElseIf typ2 = sand_CO2 And Rand(1)
								PokeByte bank,x*sizey+sizey+y+1, (2 = Rand(2)) * sand_GRAS
							EndIf
						EndIf
					Case 4
						If x > 1
							typ2 = PeekByte(bank,x*sizey-sizey+y)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey-sizey+y, (1 = Rand(1)) * sand_GRAS
							ElseIf typ2 = sand_CO2 And Rand(1)
								PokeByte bank,x*sizey-sizey+y, (2 = Rand(2)) * sand_GRAS
							EndIf
						EndIf
					Case 5
						If x < sizex-2
							typ2 = PeekByte(bank,x*sizey+sizey+y)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey+sizey+y, (1 = Rand(1)) * sand_GRAS
							ElseIf typ2 = sand_CO2 And Rand(1)
								PokeByte bank,x*sizey+sizey+y, (2 = Rand(2)) * sand_GRAS
							EndIf
						EndIf
					Case 6
						If x > 1 And y >1
							typ2 = PeekByte(bank,x*sizey-sizey+y-1)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey-sizey+y-1, (1 = Rand(1)) * sand_GRAS
							ElseIf typ2 = sand_CO2 And Rand(1)
								PokeByte bank,x*sizey-sizey+y-1, (2 = Rand(2)) * sand_GRAS
							EndIf
						EndIf
					Case 7
						If y > 1
							typ2 = PeekByte(bank,off-1)
							If typ2 = sand_WATER
								PokeByte bank,off-1, (1 = Rand(1)) * sand_GRAS
							ElseIf typ2 = sand_CO2 And Rand(1)
								PokeByte bank,x*sizey+y-1, (2 = Rand(2)) * sand_GRAS
							EndIf
						EndIf
					Case 8
						If x < sizex-2 And y > 1
							typ2 = PeekByte(bank,x*sizey+sizey+y-1)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey+sizey+y-1, (1 = Rand(1)) * sand_GRAS
							ElseIf typ2 = sand_CO2 And Rand(1)
								PokeByte bank,x*sizey+sizey+y-1, (2 = Rand(2)) * sand_GRAS
							EndIf
						EndIf
					Case 38
						If 1 = Rand(100) Then	PokeByte bank,off, (1 = Rand(1)) * sand_OIL
					End Select
				Case sand_SPOUT
					If Rand(0,30)=4 And y > 1
						typ2 = PeekByte(bank,off-1)
						If typ2 = sand_EMPTY
							PokeByte bank,off-1,2
						EndIf
					EndIf
				Case sand_CO2, sand_STEAM
					typo = PeekByte(bank,off+1)
					If Rand(300)=1 And typ = sand_STEAM Then
						typ = sand_WATER
						PokeByte bank,off,typ
					Else
						If y < sizey-1 And Rand(0,6)=2 And typo = sand_EMPTY ;<> sand_STONE And typo <> sand_GRAS And typo <> sand_SPOUT And typo <> sand_HEATER And typo<>sand_COOLER
							PokeByte bank,off,typo
							If y < sizey-2 Then PokeByte bank,off+1,typ
							typ = typo
						Else ; expandieren
							r = Rand(1,9)
							Select r
							Case 1
								If x > 0 And y < sizey-1
									typ2 = PeekByte(bank,x*sizey-sizey+y+1)
									If typ2 = sand_EMPTY
										PokeByte bank,x*sizey-sizey+y+1,typ
										PokeByte bank,off,typ2
										typ = typ2
									EndIf
								EndIf
							Case 2
								If y < sizey-1
									typ2 = PeekByte(bank,off+1)
									If typ2 = sand_EMPTY
										PokeByte bank,off+1,typ
										PokeByte bank,off,typ2
										typ = typ2
									EndIf
								EndIf
							Case 3
								If x < sizex-1 And y < sizey-1
									typ2 = PeekByte(bank,x*sizey+sizey+y+1)
									If typ2 = sand_EMPTY
										PokeByte bank,x*sizey+sizey+y+1,typ
										PokeByte bank,off,typ2
										typ = typ2
									EndIf
								EndIf
							Case 4
								If x > 0
									typ2 = PeekByte(bank,x*sizey-sizey+y)
									If typ2 = sand_EMPTY
										PokeByte bank,x*sizey-sizey+y,typ
										PokeByte bank,off,typ2
										typ = typ2
									EndIf
								EndIf
							Case 5
								If x < sizex-1
									typ2 = PeekByte(bank,x*sizey+sizey+y)
									If typ2 = sand_EMPTY
										PokeByte bank,x*sizey+sizey+y,typ
										PokeByte bank,off,typ2
										typ = typ2
									EndIf
								EndIf
							Case 6
								If x > 0 And y >0
									typ2 = PeekByte(bank,x*sizey-sizey+y-1)
									If typ2 = sand_EMPTY
										PokeByte bank,x*sizey-sizey+y-1,typ
										PokeByte bank,off,typ2
										typ = typ2
									EndIf
								EndIf
							Case 7
								If y > 0
									typ2 = PeekByte(bank,off-1)
									If typ2 = sand_EMPTY
										PokeByte bank,off-1,typ
										PokeByte bank,off,typ2
										typ = typ2
									EndIf
								EndIf
							Case 8
								If x < sizex-1 And y > 0
									typ2 = PeekByte(bank,x*sizey+sizey+y-1)
									If typ2 = sand_EMPTY
										PokeByte bank,x*sizey+sizey+y-1,typ
										PokeByte bank,off,typ2
										typ = typ2
									EndIf
								EndIf
							End Select
							
							If typ = showtyp
								Select Rand(0,1)
								Case 0
									If x > 1 And x < sizex-2
										typ2 = PeekByte(bank,off-sizey)
										typ3 = PeekByte(bank,off+sizey)
										If typ2 <> sand_EMPTY And typ3 = sand_EMPTY Then
											PokeByte bank,off+sizey,typ
											PokeByte bank,off,typ3
											typ = typ3
										ElseIf typ2 = sand_EMPTY And typ3 <> sand_EMPTY Then
											PokeByte bank,off-sizey,typ
											PokeByte bank,off,typ2
											typ = typ2
										ElseIf typ2 = sand_EMPTY And typ3 = sand_EMPTY And Rand(0,1) And y < sizey-1
											typ2 = PeekByte(bank,off+1)
											If typ2 Then
												Select Rand(0,1)
												Case 0
													PokeByte bank,off-sizey,typ
													PokeByte bank,off,0
													typ = sand_EMPTY
												Case 1
													PokeByte bank,off+sizey,typ
													PokeByte bank,off,0
													typ = sand_EMPTY
												End Select
											ElseIf y < sizey-2
												typ2 = PeekByte(bank,off+2)
												If typ2 Then
													Select Rand(0,1)
													Case 0
														PokeByte bank,off-sizey,typ
														PokeByte bank,off,0
														typ = sand_EMPTY
													Case 1
														PokeByte bank,off+sizey,typ
														PokeByte bank,off,0
														typ = sand_EMPTY
													End Select
												EndIf
											EndIf
										ElseIf typ2 <> sand_EMPTY And typ3 <> sand_EMPTY And x > 2 And x < sizex-3 And False
											typ2 = PeekByte(bank,off-sizey*2)
											typ3 = PeekByte(bank,off+sizey*2)
											If typ2 <> sand_EMPTY And typ3 = sand_EMPTY Then
												PokeByte bank,off+sizey*2,typ
												PokeByte bank,off,typ3
												typ = typ3
											ElseIf typ2 = sand_EMPTY And typ3 <> sand_EMPTY Then
												PokeByte bank,off-sizey*2,typ
												PokeByte bank,off,typ2
												typ = typ2
											EndIf
										EndIf
									EndIf
								Case 1
									If y > 1 And y < sizey-2
										typ2 = PeekByte(bank,off-1)
										typ3 = PeekByte(bank,off+1)
										If typ2 <> sand_EMPTY And typ3 = sand_EMPTY Then
											PokeByte bank,off+1,typ
											PokeByte bank,off,typ3
											typ = typ3
										ElseIf typ2 = sand_EMPTY And typ3 <> sand_EMPTY Then
											PokeByte bank,off-1,typ
											PokeByte bank,off,typ2
											typ = typ2
										ElseIf typ2 <> sand_EMPTY And typ3 <> sand_EMPTY And Rand(1) And y > 2 And y < sizey-3 And False
											typ2 = PeekByte(bank,off-2)
											typ3 = PeekByte(bank,off+2)
											If typ2 <> sand_EMPTY And typ3 = sand_EMPTY Then
												PokeByte bank,off+2,typ
												PokeByte bank,off,typ3
												typ = typ3
											ElseIf typ2 = sand_EMPTY And typ3 <> sand_EMPTY Then
												PokeByte bank,off-2,typ
												PokeByte bank,off,typ2
												typ = typ2
											EndIf
										EndIf
									EndIf
								End Select
							EndIf
						EndIf
					EndIf
				Case sand_HEATER ; herd
					r = Rand(1,10)
					Select r
					Case 1
						If x > 1 And y < sizey-2
							typ2 = PeekByte(bank,x*sizey-sizey+y+1)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey-sizey+y+1,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> sand_FIRE
								PokeByte bank,x*sizey-sizey+y+1,5
							EndIf
						EndIf
					Case 2
						If y < sizey-2
							typ2 = PeekByte(bank,off+1)
							If typ2 = sand_WATER
								PokeByte bank,off+1,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> sand_FIRE
								PokeByte bank,off+1,5
							EndIf
						EndIf
					Case 3
						If x < sizex-2 And y < sizey
							typ2 = PeekByte(bank,x*sizey+sizey+y+1)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey+sizey+y+1,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> sand_FIRE
								PokeByte bank,x*sizey+sizey+y+1,5
							EndIf
						EndIf
					Case 4
						If x > 1
							typ2 = PeekByte(bank,x*sizey-sizey+y)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey-sizey+y,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> sand_FIRE
								PokeByte bank,x*sizey-sizey+y,5
							EndIf
						EndIf
					Case 5
						If x < sizex-2
							typ2 = PeekByte(bank,x*sizey+sizey+y)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey+sizey+y,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> sand_FIRE
								PokeByte bank,x*sizey+sizey+y,5
							EndIf
						EndIf
					Case 6
						If x > 1 And y >1
							typ2 = PeekByte(bank,x*sizey-sizey+y-1)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey-sizey+y-1,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> sand_FIRE
								PokeByte bank,x*sizey-sizey+y-1,5
							EndIf
						EndIf
					Case 7
						If y > 1
							typ2 = PeekByte(bank,off-1)
							If typ2 = sand_WATER
								PokeByte bank,off-1,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> sand_FIRE
								PokeByte bank,off-1,5
							EndIf
						EndIf
					Case 8
						If x < sizex-2 And y > 1
							typ2 = PeekByte(bank,x*sizey+sizey+y-1)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey+sizey+y-1,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> sand_FIRE
								PokeByte bank,x*sizey+sizey+y-1,5
							EndIf
						EndIf
					End Select
				Case sand_COOLER ; kühler
					r = Rand(1,10)
					Select r
					Case 1
						If x > 1 And y < sizey-2
							typ2 = PeekByte(bank,x*sizey-sizey+y+1)
							If typ2 = sand_STEAM
								PokeByte bank,x*sizey-sizey+y+1,2
							EndIf
						EndIf
					Case 2
						If y < sizey-2
							typ2 = PeekByte(bank,off+1)
							If typ2 = sand_STEAM
								PokeByte bank,off+1,2
							EndIf
						EndIf
					Case 3
						If x < sizex-2 And y < sizey
							typ2 = PeekByte(bank,x*sizey+sizey+y+1)
							If typ2 = sand_STEAM
								PokeByte bank,x*sizey+sizey+y+1,2
							EndIf
						EndIf
					Case 4
						If x > 1
							typ2 = PeekByte(bank,x*sizey-sizey+y)
							If typ2 = sand_STEAM
								PokeByte bank,x*sizey-sizey+y,2
							EndIf
						EndIf
					Case 5
						If x < sizex-2
							typ2 = PeekByte(bank,x*sizey+sizey+y)
							If typ2 = sand_STEAM
								PokeByte bank,x*sizey+sizey+y,2
							EndIf
						EndIf
					Case 6
						If x > 1 And y >1
							typ2 = PeekByte(bank,x*sizey-sizey+y-1)
							If typ2 = sand_STEAM
								PokeByte bank,x*sizey-sizey+y-1,2
							EndIf
						EndIf
					Case 7
						If y > 1
							typ2 = PeekByte(bank,off-1)
							If typ2 = sand_STEAM
								PokeByte bank,off-1,2
							EndIf
						EndIf
					Case 8
						If x < sizex-2 And y > 1
							typ2 = PeekByte(bank,x*sizey+sizey+y-1)
							If typ2 = sand_STEAM
								PokeByte bank,x*sizey+sizey+y-1,2
							EndIf
						EndIf
					End Select

				End Select

				If showtyp <> sand_EMPTY Then
					If showtyp2 <> showtyp Then
						WritePixelFast x,sizey-y, sand_color_light[showtyp]
					ElseIf typo2 <> showtyp Then
						WritePixelFast x,sizey-y, sand_color_dark[showtyp]
					Else
						WritePixelFast x,sizey-y, sand_color[showtyp]
					EndIf
				EndIf
				typo2 = showtyp
				typo = typ
			Next
		Wend
	Next
	
	UnlockBuffer BackBuffer()
End Function

Function Sand_Clear()
	For x = 0 To sizex-1
		For y = 0 To sizey-1
			PokeByte(bank,x*sizey+y,0)
		Next
	Next
	For o.TObject = Each TObject
		Delete o
	Next
End Function

	
Function Sand_Save()
	Repeat
		i = i + 1
		If FileType(i+".sand") <> 1 Then
			file = WriteFile(i+".sand")
			For o.TObject = Each TObject
				WriteByte file,1
				WriteByte file,o\typ
				WriteShort file,o\x
				WriteShort file,o\y
				WriteShort file,o\size
				WriteShort file,o\intensity
				WriteByte file,o\particles
			Next
			WriteByte file,2
			WriteBytes(bank,file,0,BankSize(bank))
			CloseFile file
			Exit
		EndIf
	Forever
End Function

Function Sand_Load(path$)
	If FileType(path$) = 0 Then
		If FileType(path$+".sand") = 0 Then	
			Return 0
		Else
			path = path + ".sand"
		EndIf
	EndIf
	file = ReadFile(path$)
	Sand_Clear()
	While ReadByte(file)=1
		o.TObject	= New TObject
		o\typ		= ReadByte(file)
		o\x			= ReadShort(file)
		o\y			= ReadShort(file)
		o\size		= ReadShort(file)
		o\intensity	= ReadShort(file)
		o\particles	= ReadByte(file)
	Wend
	ReadBytes bank,file,0,BankSize(bank)
End Function


Function Sand_SaveB()
	Graphics 640,480,32,2
	img = CreateImage(sizex,sizey)
	SetBuffer ImageBuffer(img)
	LockBuffer ImageBuffer(img)
	For x = 0 To sizex-1
		For y = 0 To sizey-1
			mat = PeekByte(bank,x*sizey+y)
			WritePixelFast x,sizey-y-1,sand_color[mat]
		Next
	Next
	UnlockBuffer ImageBuffer(img)
	Repeat
		i = i + 1
		If FileType(i+".bmp") <> 1 Then
			SaveImage(img,i+".bmp")
			Exit
		EndIf
	Forever
	FreeImage img
	Graphics 640,480,16,2
	SetBuffer BackBuffer()
	SetFont LoadFont("arial",14,True)
End Function

Function Sand_LoadB(path$)
	If FileType(path$) = 0 Then
		If FileType(path$+".bmp") = 0 Then	
			Return 0
		Else
			path = path + ".bmp"
		EndIf
	EndIf
	Graphics 640,480,32,2
	img = LoadImage(path$)
	If img = 0 Then Return 0
	SetBuffer ImageBuffer(img)
	LockBuffer ImageBuffer(img)
	For x = 0 To sizex-1
		For y = 0 To sizey-1
			col = ReadPixelFast(x,y)
			Select col
			Case sand_EMPTY ; nichts
				mat = 0
			Case $ccccaa ; sand
				mat = 1
			Case $0000ff ; wasser
				mat = sand_WATER
			Case $888888 ; stein
				mat = sand_STONE
			Case $aa9933 ; öl
				mat = sand_OIL
			Case $ff8800 ; feuer
				mat = sand_FIRE
			Case $00cc11 ; gras
				mat = sand_GRAS
			Case $40a0ff ; wolke
				mat = sand_SPOUT
			Case $604030 ; co2 (gas)
				mat = sand_CO2
			Case $0030a0  ; wasserdampf
				mat = sand_STEAM
			Case $ff0033; säure
				mat = sand_ACID
			Case $ed8640; herd
				mat = sand_HEATER
			Case $88ddff; kühler
				mat = sand_COOLER
			End Select
			PokeByte(bank,x*sizey+y,mat)
		Next
	Next
	UnlockBuffer ImageBuffer(img)
	FreeImage img
	Graphics 640,480,16,2
	SetBuffer BackBuffer()
	SetFont LoadFont("arial",14,True)
End Function