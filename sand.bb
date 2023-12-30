CONST sand_EMPTY	= 0
CONST sand_SAND		= 1
CONST sand_WATER	= 2
CONST sand_STONE	= 3
CONST sand_OIL		= 4
CONST sand_FIRE		= 5
CONST sand_GRAS		= 6
CONST sand_SPOUT	= 7
CONST sand_CO2		= 8
CONST sand_STEAM	= 9
CONST sand_ACID		= 10
CONST sand_HEATER	= 11
CONST sand_COOLER	= 12

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
					
				Case sand_SAND,2,4,sand_ACID ; salz, wasserr, öl, säure
					moved = 0
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
								ElseIf typ2 <> sand_EMPTY And typ2 < sand_ACID And typ2 <> 8 And Rand(0,1)
									If Rand(0,1) Then PokeByte bank,off-sizey+1,typ
									PokeByte bank,off,8
									typ = 8
								EndIf
							EndIf
						Case 2
							If y < sizey-1
								typ2 = PeekByte(bank,off+1)
								If typ2 = sand_WATER
									If Rand(0,400) Then PokeByte bank,off+1,typ
									PokeByte bank,off,typ2
									typ = typ2
								ElseIf typ2 <> sand_EMPTY And typ2 < sand_ACID And typ2 <> 8 And Rand(0,1)
									If Rand(0,1) Then PokeByte bank,off+1,typ
									PokeByte bank,off,8
									typ = 8
								EndIf
							EndIf
						Case 3
							If x < sizex-1 And y < sizey
								typ2 = PeekByte(bank,x*sizey+sizey+y+1)
								If typ2 = sand_WATER
									If Rand(0,400) Then PokeByte bank,x*sizey+sizey+y+1,typ
									PokeByte bank,off,typ2
									typ = typ2
								ElseIf typ2 <> sand_EMPTY And typ2 < sand_ACID And typ2 <> 8 And Rand(0,1)
									If Rand(0,1) Then PokeByte bank,off+sizey+1,typ
									PokeByte bank,off,8
									typ = 8
								EndIf
							EndIf
						Case 4
							If x > 0
								typ2 = PeekByte(bank,x*sizey-sizey+y)
								If typ2 = sand_WATER
									If Rand(0,400) Then PokeByte bank,x*sizey-sizey+y,typ
									PokeByte bank,off,typ2
									typ = typ2
								ElseIf typ2 <> sand_EMPTY And typ2 < sand_ACID And typ2 <> 8 And Rand(0,1)
									If Rand(0,1) Then PokeByte bank,off-sizey,typ
									PokeByte bank,off,8
									typ = 8
								EndIf
							EndIf
						Case 5
							If x < sizex-1
								typ2 = PeekByte(bank,x*sizey+sizey+y)
								If typ2 = sand_WATER
									If Rand(0,400) Then PokeByte bank,x*sizey+sizey+y,typ
									PokeByte bank,off,typ2
									typ = typ2
								ElseIf typ2 <> sand_EMPTY And typ2 < sand_ACID And typ2 <> 8 And Rand(0,1)
									If Rand(0,1) Then PokeByte bank,off+sizey,typ
									PokeByte bank,off,8
									typ = 8
								EndIf
							EndIf
						Case 6
							If x > 0 And y >0
								typ2 = PeekByte(bank,x*sizey-sizey+y-1)
								If typ2 = sand_WATER
									If Rand(0,400) Then PokeByte bank,x*sizey-sizey+y-1,typ
									PokeByte bank,off,typ2
									typ = typ2
								ElseIf typ2 <> sand_EMPTY And typ2 < sand_ACID And typ2 <> 8 And Rand(0,1)
									If Rand(0,1) Then PokeByte bank,off-sizey-1,typ
									PokeByte bank,off,8
									typ = 8
								EndIf
							EndIf
						Case 7
							If y > 0
								typ2 = PeekByte(bank,off-1)
								If typ2 = sand_WATER
									If Rand(0,400) Then PokeByte bank,off-1,typ
									PokeByte bank,off,typ2
									typ = typ2
								ElseIf typ2 <> sand_EMPTY And typ2 < sand_ACID And typ2 <> 8 And Rand(0,1)
									If Rand(0,1) Then PokeByte bank,off-1,typ
									PokeByte bank,off,8
									typ = 8
								EndIf
							EndIf
						Case 8
							If x < sizex-1 And y > 0
								typ2 = PeekByte(bank,x*sizey+sizey+y-1)
								If typ2 = sand_WATER
									If Rand(0,400) Then PokeByte bank,x*sizey+sizey+y-1,typ
									PokeByte bank,off,typ2
									typ = typ2
								ElseIf typ2 <> sand_EMPTY And typ2 < sand_ACID And typ2 <> 8 And Rand(0,1)
									If Rand(0,1) Then PokeByte bank,off+sizey-1,typ
									PokeByte bank,off,8
									typ = 8
								EndIf
							EndIf
						End Select
					EndIf
					If typo = sand_EMPTY Or typo = 8 Or typo=9 Or (typ = sand_SAND And (typo = sand_WATER Or typo=4)) Or (typ = sand_WATER And typo=4) Then
						If typo = sand_EMPTY Or Rand(0,1)
							If Rand(10)>1 
								PokeByte bank,off,typo
								If y > 0 Then PokeByte bank,off-1,typ
								typ = typo
							EndIf
						EndIf
					ElseIf y > 0 
						If typ = sand_WATER Or typ = 4
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
							
							
							If typ2 = typ And (typ3 = sand_EMPTY Or (typ3 = 4 And typ = sand_WATER) Or ((typ3=8 Or typ3=9) And Rand(0,1)))
								PokeByte bank,off,typ3
								PokeByte bank,x*sizey-sizey+y,typ
								typ = typ3
								moved = 1
							ElseIf typ3 = typ And (typ2 = sand_EMPTY Or (typ2 = 4 And typ = sand_WATER) Or ((typ2=8 Or typ2=9) And Rand(0,1)))
								PokeByte bank,off,typ2
								PokeByte bank,x*sizey+sizey+y,typ
								typ = typ2
								moved = 1
							EndIf
						EndIf
						If typ = sand_WATER Or typ = 4 Or typ = sand_ACID
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
						
							
							If typ2 = typ And (typ3 = sand_EMPTY Or (typ3 = 4 And typ = sand_WATER))
								PokeByte bank,off,typ3
								PokeByte bank,x*sizey-sizey+y,typ
								typ = typ3
								moved = 1
							ElseIf typ3 = typ And (typ2 = sand_EMPTY Or (typ2 = 4 And typ = sand_WATER))
								PokeByte bank,off,typ2
								PokeByte bank,x*sizey+sizey+y,typ
								typ = typ2
								moved = 1
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
						
							
							If typ2 = typ And (typ3 = sand_EMPTY Or (typ3 = 4 And typ = sand_WATER))
								PokeByte bank,off,typ3
								PokeByte bank,x*sizey-sizey+y,typ
								typ = typ3
								moved = 1
							ElseIf typ3 = typ And (typ2 = sand_EMPTY Or (typ2 = 4 And typ = sand_WATER))
								PokeByte bank,off,typ2
								PokeByte bank,x*sizey+sizey+y,typ
								typ = typ2
								moved = 1
							EndIf
						EndIf
						
					
						If Rand(1,2) = 1 And x < sizex-2
							typ2 = PeekByte(bank,x*sizey+sizey+y-1)
							If typ2 = sand_EMPTY Or (typ2 = sand_WATER And typ = sand_SAND And Rand(0,1)) Or ((typ2 = 4 Or typ2 = 8 Or typ2=9) And Rand(0,1))
								PokeByte bank,off,typ2
								PokeByte bank,x*sizey+sizey+y-1,typ
								typ = typ2
								moved = 1
							ElseIf Rand(0,1) And x < sizex-3
								typ2 = PeekByte(bank,x*sizey+sizey*2+y-1)
								If typ2 = sand_EMPTY Or (typ2 = sand_WATER And typ = sand_SAND And Rand(0,1)) Or ((typ2 = 4 Or typ2 = 8 Or typ2=9) And Rand(0,1))
									PokeByte bank,off,typ2
									PokeByte bank,x*sizey+sizey*2+y-1,typ
									typ = typ2
									moved = 1
								EndIf
							EndIf
						ElseIf x > 1
							typ2 = PeekByte(bank,x*sizey-sizey+y-1)
							If typ2 = sand_EMPTY Or (typ2 = sand_WATER And typ = sand_SAND And Rand(0,1)) Or ((typ2 = 4 Or typ2 = 8 Or typ2=9) And Rand(0,1))
								PokeByte bank,off,typ2
								PokeByte bank,x*sizey-sizey+y-1,typ
								typ = typ2
								moved = 1
							ElseIf Rand(0,1) And x > 2
								typ2 = PeekByte(bank,x*sizey-sizey*2+y-1)
								If typ2 = sand_EMPTY Or (typ2 = sand_WATER And typ = sand_SAND And Rand(0,1)) Or ((typ2 = 4 Or typ2 = 8 Or typ2=9) And Rand(0,1))
									PokeByte bank,off,typ2
									PokeByte bank,x*sizey-sizey*2+y-1,typ
									typ = typ2
									moved = 1
								EndIf
							EndIf
						EndIf
					EndIf
				Case 5 ; feuer
					If Rand(50)=1
						PokeByte bank,off,8
						If PeekByte(bank,off+1) = 2 Then PokeByte bank,off,9
						typ = 8
					ElseIf Rand(0,1) And y < sizey-2
						typ2 = PeekByte(bank,off+1)
						Select typ2
						Case sand_EMPTY
							If Rand(2)=2
								PokeByte bank,off,typ2
								PokeByte bank,off+1,typ
								typ = typ2
							EndIf
						Case sand_SAND,3,sand_HEATER
							If Rand(10)=2
								PokeByte bank,off,0
								typ = sand_EMPTY
							EndIf
						Case sand_WATER,sand_COOLER
							PokeByte bank,off,9
							typ = 9
						Case 4,6
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
							Case sand_SAND,3,sand_HEATER
								If Rand(10)=2
									PokeByte bank,off,0
									typ = sand_EMPTY
								EndIf
							Case sand_WATER,sand_COOLER
								If Rand(2)=1
									PokeByte bank,off,0
									typ = sand_EMPTY
								EndIf
							Case 4,6
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
							Case sand_SAND,3,sand_HEATER
								If Rand(10)=2
									PokeByte bank,off,0
									typ = sand_EMPTY
								EndIf
							Case sand_WATER,sand_COOLER
								If Rand(2)=1
									PokeByte bank,off,0
									typ = sand_EMPTY
								EndIf
							Case 4,6
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
							Case sand_SAND,3,sand_HEATER
								If Rand(10)=2
									PokeByte bank,off,0
									typ = sand_EMPTY
								EndIf
							Case sand_WATER,sand_COOLER
								PokeByte bank,off,0
								typ = sand_EMPTY
							Case 4,6
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
							Case sand_SAND,3,sand_HEATER
								If Rand(10)=2
									PokeByte bank,off,0
									typ = sand_EMPTY
								EndIf
							Case sand_WATER,sand_COOLER
								If Rand(2-(typ2=sand_COOLER))=1
									PokeByte bank,off,0
									typ = sand_EMPTY
								EndIf
							Case 4,6
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
							Case sand_SAND,3,sand_HEATER
								If Rand(10)=2
									PokeByte bank,off,0
									typ = sand_EMPTY
								EndIf
							Case sand_WATER,sand_COOLER
								If Rand(2-(typ2=sand_COOLER))=1
									PokeByte bank,off,0
									typ = sand_EMPTY
								EndIf
							Case 4,6
								If Rand(0,1)
									PokeByte bank,x*sizey-sizey+y,typ
									typ = typ
								EndIf
							End Select
						EndIf
					EndIf
				Case 6 ; pflanze
					r = Rand(1,80)
					Select r
					Case 1
						If x > 1 And y < sizey-2
							typ2 = PeekByte(bank,x*sizey-sizey+y+1)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey-sizey+y+1,typ
							ElseIf typ2 = 8 And Rand(1)
								PokeByte bank,x*sizey-sizey+y+1,0
							EndIf
						EndIf
					Case 2
						If y < sizey-2
							typ2 = PeekByte(bank,off+1)
							If typ2 = sand_WATER
								PokeByte bank,off+1,typ
							ElseIf typ2 = 8 And Rand(1)
								PokeByte bank,x*sizey+y-1,0
							EndIf
						EndIf
					Case 3
						If x < sizex-2 And y < sizey
							typ2 = PeekByte(bank,x*sizey+sizey+y+1)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey+sizey+y+1,typ
							ElseIf typ2 = 8 And Rand(1)
								PokeByte bank,x*sizey+sizey+y+1,0
							EndIf
						EndIf
					Case 4
						If x > 1
							typ2 = PeekByte(bank,x*sizey-sizey+y)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey-sizey+y,typ
							ElseIf typ2 = 8 And Rand(1)
								PokeByte bank,x*sizey-sizey+y,0
							EndIf
						EndIf
					Case 5
						If x < sizex-2
							typ2 = PeekByte(bank,x*sizey+sizey+y)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey+sizey+y,typ
							ElseIf typ2 = 8 And Rand(1)
								PokeByte bank,x*sizey+sizey+y,0
							EndIf
						EndIf
					Case 6
						If x > 1 And y >1
							typ2 = PeekByte(bank,x*sizey-sizey+y-1)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey-sizey+y-1,typ
							ElseIf typ2 = 8 And Rand(1)
								PokeByte bank,x*sizey-sizey+y-1,0
							EndIf
						EndIf
					Case 7
						If y > 1
							typ2 = PeekByte(bank,off-1)
							If typ2 = sand_WATER
								PokeByte bank,off-1,typ
							ElseIf typ2 = 8 And Rand(1)
								PokeByte bank,x*sizey+y-1,0
							EndIf
						EndIf
					Case 8
						If x < sizex-2 And y > 1
							typ2 = PeekByte(bank,x*sizey+sizey+y-1)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey+sizey+y-1,typ
							ElseIf typ2 = 8 And Rand(1)
								PokeByte bank,x*sizey+sizey+y-1,0
							EndIf
						EndIf
					Case 38
						If Rand(20)=1 Then	PokeByte bank,off,4
					End Select
				Case 7 ; wolke
					If Rand(0,30)=4 And y > 1
						typ2 = PeekByte(bank,off-1)
						If typ2 = sand_EMPTY
							PokeByte bank,off-1,2
						EndIf
					EndIf
				Case 8,9 ; wasser/co2-gas
					moved = 0
					typo = PeekByte(bank,off+1)
					If Rand(300)=1 And typ = 9 Then
						typ = sand_WATER
						PokeByte bank,off,typ
					Else
						If y < sizey-1 And Rand(0,6)=2 And typo = sand_EMPTY ;<> 3 And typo <> 6 And typo <> 7 And typo <> sand_HEATER And typo<>sand_COOLER
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
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> 5
								PokeByte bank,x*sizey-sizey+y+1,5
							EndIf
						EndIf
					Case 2
						If y < sizey-2
							typ2 = PeekByte(bank,off+1)
							If typ2 = sand_WATER
								PokeByte bank,off+1,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> 5
								PokeByte bank,off+1,5
							EndIf
						EndIf
					Case 3
						If x < sizex-2 And y < sizey
							typ2 = PeekByte(bank,x*sizey+sizey+y+1)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey+sizey+y+1,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> 5
								PokeByte bank,x*sizey+sizey+y+1,5
							EndIf
						EndIf
					Case 4
						If x > 1
							typ2 = PeekByte(bank,x*sizey-sizey+y)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey-sizey+y,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> 5
								PokeByte bank,x*sizey-sizey+y,5
							EndIf
						EndIf
					Case 5
						If x < sizex-2
							typ2 = PeekByte(bank,x*sizey+sizey+y)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey+sizey+y,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> 5
								PokeByte bank,x*sizey+sizey+y,5
							EndIf
						EndIf
					Case 6
						If x > 1 And y >1
							typ2 = PeekByte(bank,x*sizey-sizey+y-1)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey-sizey+y-1,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> 5
								PokeByte bank,x*sizey-sizey+y-1,5
							EndIf
						EndIf
					Case 7
						If y > 1
							typ2 = PeekByte(bank,off-1)
							If typ2 = sand_WATER
								PokeByte bank,off-1,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> 5
								PokeByte bank,off-1,5
							EndIf
						EndIf
					Case 8
						If x < sizex-2 And y > 1
							typ2 = PeekByte(bank,x*sizey+sizey+y-1)
							If typ2 = sand_WATER
								PokeByte bank,x*sizey+sizey+y-1,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> 5
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
							If typ2 = 9
								PokeByte bank,x*sizey-sizey+y+1,2
							EndIf
						EndIf
					Case 2
						If y < sizey-2
							typ2 = PeekByte(bank,off+1)
							If typ2 = 9
								PokeByte bank,off+1,2
							EndIf
						EndIf
					Case 3
						If x < sizex-2 And y < sizey
							typ2 = PeekByte(bank,x*sizey+sizey+y+1)
							If typ2 = 9
								PokeByte bank,x*sizey+sizey+y+1,2
							EndIf
						EndIf
					Case 4
						If x > 1
							typ2 = PeekByte(bank,x*sizey-sizey+y)
							If typ2 = 9
								PokeByte bank,x*sizey-sizey+y,2
							EndIf
						EndIf
					Case 5
						If x < sizex-2
							typ2 = PeekByte(bank,x*sizey+sizey+y)
							If typ2 = 9
								PokeByte bank,x*sizey+sizey+y,2
							EndIf
						EndIf
					Case 6
						If x > 1 And y >1
							typ2 = PeekByte(bank,x*sizey-sizey+y-1)
							If typ2 = 9
								PokeByte bank,x*sizey-sizey+y-1,2
							EndIf
						EndIf
					Case 7
						If y > 1
							typ2 = PeekByte(bank,off-1)
							If typ2 = 9
								PokeByte bank,off-1,2
							EndIf
						EndIf
					Case 8
						If x < sizex-2 And y > 1
							typ2 = PeekByte(bank,x*sizey+sizey+y-1)
							If typ2 = 9
								PokeByte bank,x*sizey+sizey+y-1,2
							EndIf
						EndIf
					End Select

				End Select
				
				
				;If iterations = i 
				If showtyp2 <> showtyp ; hell
					Select showtyp
					Case sand_EMPTY ; nichts
						col = 0
					Case sand_SAND ; sand
						col = $ffffff
					Case sand_WATER ; wasser
						col = $3065ff
					Case 3 ; stein
						col = $c8c8c8
					Case 4 ; öl
						col = $daa953
					Case 5 ; feuer
						col = $ff8800
					Case 6 ; gras
						col = $40ec61
					Case 7 ; wolke
						col = $60c0ff
					Case 8 ; co2 (gas)
						col = $604030
					Case 9 ; wasserdampf
						col = $0030a0
					Case sand_ACID; säure
						col = $ff7498
					Case sand_HEATER; herd
						col = $ed8640
					Case sand_COOLER; kühler
						col = $88ddff
					End Select
					If col WritePixelFast x,sizey-y,col
				ElseIf typo2 <> showtyp ; dunkel
					Select showtyp
					Case sand_EMPTY ; nichts
						col = 0
					Case sand_SAND ; sand
						col = $bbbbbb
					Case sand_WATER ; wasser
						col = $0000bb
					Case 3 ; stein
						col = $555555
					Case 4 ; öl
						col = $774400
					Case 5 ; feuer
						col = $ff8800
					Case 6 ; gras
						col = $009900
					Case 7 ; wolke
						col = $1188aa
					Case 8 ; co2 (gas)
						col = $604030
					Case 9 ; wasserdampf
						col = $0030a0
					Case sand_ACID; säure
						col = $aa0000
					Case sand_HEATER; herd
						col = $ed8640
					Case sand_COOLER; kühler
						col = $88ddff
					End Select
					If col WritePixelFast x,sizey-y,col
				Else
					Select showtyp
					Case sand_EMPTY ; nichts
						col = 0
					Case sand_SAND ; sand
						col = $ccccaa
					Case sand_WATER ; wasser
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
					Case sand_ACID; säure
						col = $ff0033
					Case sand_HEATER; herd
						col = $ed8640
					Case sand_COOLER; kühler
						col = $88ddff
					End Select
					If col WritePixelFast x,sizey-y,col
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
			col = 0
			Select mat
			Case sand_EMPTY ; nichts
				col = 0
			Case sand_SAND ; sand
				col = $ccccaa
			Case sand_WATER ; wasser
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
			Case sand_ACID; säure
				col = $ff0033
			Case sand_HEATER; herd
				col = $ed8640
			Case sand_COOLER; kühler
				col = $88ddff
			End Select
			If col WritePixelFast x,sizey-y-1,col
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
				mat = 2
			Case $888888 ; stein
				mat = 3
			Case $aa9933 ; öl
				mat = 4
			Case $ff8800 ; feuer
				mat = 5
			Case $00cc11 ; gras
				mat = 6
			Case $40a0ff ; wolke
				mat = 7
			Case $604030 ; co2 (gas)
				mat = 8
			Case $0030a0  ; wasserdampf
				mat = 9
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