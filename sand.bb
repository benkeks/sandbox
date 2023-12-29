Function UpdateSand()
	LockBuffer BackBuffer()
	
	For i = 1 To 1
		ri = 1-ri
		x = (sizex-1)*(ri=1)
		Ende = (sizex-1)*(ri=0)
		While (x < ende And ri = 0) Or (x > ende And ri = 1)
			If ri = 1 Then x = x - 1 Else x = x + 1
			typo = 0
			For y = 0 To sizey-1
				off = x*sizey+y
				typ = PeekByte(bank,off)
				showtyp = typ
				showtyp2 = PeekByte(bank,off+1)
				
				
				
				Select typ
				Case 0,3
					
				Case 1,2,4,10 ; salz, wasserr, öl, säure
					moved = 0
					If typ = 10 And Rand(5)=5; ätzen
						; lösen!
						If False ; rem
							Select Rand(0,1)
							Case 0
								If x > 1 And x < sizex-2
									typ2 = PeekByte(bank,off-sizey)
									typ3 = PeekByte(bank,off+sizey)
									If typ2 <> 2 And typ3 = 2 Then
										PokeByte bank,off+sizey,typ
										PokeByte bank,off,typ3
										typ = typ3
									ElseIf typ2 = 2 And typ3 <> 2 Then
										PokeByte bank,off-sizey,typ
										PokeByte bank,off,typ2
										typ = typ2
									ElseIf typ2 <> 2 And typ3 <> 2 And Rand(1) And x > 2 And x < sizex-3
										typ2 = PeekByte(bank,off-sizey*2)
										typ3 = PeekByte(bank,off+sizey*2)
										If typ2 <> 2 And typ3 = 2 Then
											PokeByte bank,off+sizey*2,typ
											PokeByte bank,off,typ3
											typ = typ3
										ElseIf typ2 = 2 And typ3 <> 2 Then
											If Rand(1) Then PokeByte bank,off-sizey*2,typ
											PokeByte bank,off,typ2
											typ = typ2
										EndIf
									EndIf
								EndIf
							Case 1
								If y > 1 And y < sizey-2
									typ2 = PeekByte(bank,off-1)
									typ3 = PeekByte(bank,off+1)
									If typ2 <> 2 And typ3 = 2 Then
										PokeByte bank,off+1,typ
										PokeByte bank,off,typ3
										typ = typ3
									ElseIf typ2 = 2 And typ3 <> 2 Then
										PokeByte bank,off-1,typ
										PokeByte bank,off,typ2
										typ = typ2
									ElseIf typ2 <> 2 And typ3 <> 2 And Rand(1) And y > 2 And y < sizey-3
										typ2 = PeekByte(bank,off-2)
										typ3 = PeekByte(bank,off+2)
										If typ2 <> 2 And typ3 = 2 Then
											PokeByte bank,off+2,typ
											PokeByte bank,off,typ3
											typ = typ3
										ElseIf typ2 = 2 And typ3 <> 2 Then
											If Rand(1) Then PokeByte bank,off-2,typ
											PokeByte bank,off,typ2
											typ = typ2
										EndIf
									EndIf
								EndIf
							End Select ; endrem
						Else
							r = Rand(1,10)
							Select r
							Case 1
								If x > 0 And y < sizey-1
									typ2 = PeekByte(bank,x*sizey-sizey+y+1)
									If typ2 = 2
										If Rand(0,400) Then PokeByte bank,x*sizey-sizey+y+1,typ
										PokeByte bank,off,typ2
										typ = typ2
									ElseIf typ2 <> 0 And typ2 < 10 And typ2 <> 8 And Rand(0,1)
										If Rand(0,1) Then PokeByte bank,off-sizey+1,typ
										PokeByte bank,off,8
										typ = 8
									EndIf
								EndIf
							Case 2
								If y < sizey-1
									typ2 = PeekByte(bank,off+1)
									If typ2 = 2
										If Rand(0,400) Then PokeByte bank,off+1,typ
										PokeByte bank,off,typ2
										typ = typ2
									ElseIf typ2 <> 0 And typ2 < 10 And typ2 <> 8 And Rand(0,1)
										If Rand(0,1) Then PokeByte bank,off+1,typ
										PokeByte bank,off,8
										typ = 8
									EndIf
								EndIf
							Case 3
								If x < sizex-1 And y < sizey
									typ2 = PeekByte(bank,x*sizey+sizey+y+1)
									If typ2 = 2
										If Rand(0,400) Then PokeByte bank,x*sizey+sizey+y+1,typ
										PokeByte bank,off,typ2
										typ = typ2
									ElseIf typ2 <> 0 And typ2 < 10 And typ2 <> 8 And Rand(0,1)
										If Rand(0,1) Then PokeByte bank,off+sizey+1,typ
										PokeByte bank,off,8
										typ = 8
									EndIf
								EndIf
							Case 4
								If x > 0
									typ2 = PeekByte(bank,x*sizey-sizey+y)
									If typ2 = 2
										If Rand(0,400) Then PokeByte bank,x*sizey-sizey+y,typ
										PokeByte bank,off,typ2
										typ = typ2
									ElseIf typ2 <> 0 And typ2 < 10 And typ2 <> 8 And Rand(0,1)
										If Rand(0,1) Then PokeByte bank,off-sizey,typ
										PokeByte bank,off,8
										typ = 8
									EndIf
								EndIf
							Case 5
								If x < sizex-1
									typ2 = PeekByte(bank,x*sizey+sizey+y)
									If typ2 = 2
										If Rand(0,400) Then PokeByte bank,x*sizey+sizey+y,typ
										PokeByte bank,off,typ2
										typ = typ2
									ElseIf typ2 <> 0 And typ2 < 10 And typ2 <> 8 And Rand(0,1)
										If Rand(0,1) Then PokeByte bank,off+sizey,typ
										PokeByte bank,off,8
										typ = 8
									EndIf
								EndIf
							Case 6
								If x > 0 And y >0
									typ2 = PeekByte(bank,x*sizey-sizey+y-1)
									If typ2 = 2
										If Rand(0,400) Then PokeByte bank,x*sizey-sizey+y-1,typ
										PokeByte bank,off,typ2
										typ = typ2
									ElseIf typ2 <> 0 And typ2 < 10 And typ2 <> 8 And Rand(0,1)
										If Rand(0,1) Then PokeByte bank,off-sizey-1,typ
										PokeByte bank,off,8
										typ = 8
									EndIf
								EndIf
							Case 7
								If y > 0
									typ2 = PeekByte(bank,off-1)
									If typ2 = 2
										If Rand(0,400) Then PokeByte bank,off-1,typ
										PokeByte bank,off,typ2
										typ = typ2
									ElseIf typ2 <> 0 And typ2 < 10 And typ2 <> 8 And Rand(0,1)
										If Rand(0,1) Then PokeByte bank,off-1,typ
										PokeByte bank,off,8
										typ = 8
									EndIf
								EndIf
							Case 8
								If x < sizex-1 And y > 0
									typ2 = PeekByte(bank,x*sizey+sizey+y-1)
									If typ2 = 2
										If Rand(0,400) Then PokeByte bank,x*sizey+sizey+y-1,typ
										PokeByte bank,off,typ2
										typ = typ2
									ElseIf typ2 <> 0 And typ2 < 10 And typ2 <> 8 And Rand(0,1)
										If Rand(0,1) Then PokeByte bank,off+sizey-1,typ
										PokeByte bank,off,8
										typ = 8
									EndIf
								EndIf
							End Select
						EndIf
					EndIf
					If typo = 0 Or typo = 8 Or typo=9 Or (typ=1 And (typo=2 Or typo=4)) Or (typ=2 And typo=4) Then
						If typo = 0 Or Rand(0,1)
							If Rand(10)>1 
								PokeByte bank,off,typo
								If y > 0 Then PokeByte bank,off-1,typ
								typ = typo
							EndIf
						EndIf
					ElseIf y > 0 
						If typ = 2 Or typ = 4
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
							
							
							If typ2 = typ And (typ3 = 0 Or (typ3 = 4 And typ=2) Or ((typ3=8 Or typ3=9) And Rand(0,1)))
								PokeByte bank,off,typ3
								PokeByte bank,x*sizey-sizey+y,typ
								typ = typ3
								moved = 1
							ElseIf typ3 = typ And (typ2 = 0 Or (typ2 = 4 And typ=2) Or ((typ2=8 Or typ2=9) And Rand(0,1)))
								PokeByte bank,off,typ2
								PokeByte bank,x*sizey+sizey+y,typ
								typ = typ2
								moved = 1
							EndIf
						EndIf
						If typ = 2 Or typ = 4 Or typ = 10
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
						
							
							If typ2 = typ And (typ3 = 0 Or (typ3 = 4 And typ=2))
								PokeByte bank,off,typ3
								PokeByte bank,x*sizey-sizey+y,typ
								typ = typ3
								moved = 1
							ElseIf typ3 = typ And (typ2 = 0 Or (typ2 = 4 And typ=2))
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
						
							
							If typ2 = typ And (typ3 = 0 Or (typ3 = 4 And typ=2))
								PokeByte bank,off,typ3
								PokeByte bank,x*sizey-sizey+y,typ
								typ = typ3
								moved = 1
							ElseIf typ3 = typ And (typ2 = 0 Or (typ2 = 4 And typ=2))
								PokeByte bank,off,typ2
								PokeByte bank,x*sizey+sizey+y,typ
								typ = typ2
								moved = 1
							EndIf
						EndIf
						
					
						If Rand(1,2) = 1 And x < sizex-2
							typ2 = PeekByte(bank,x*sizey+sizey+y-1)
							If typ2 = 0 Or (typ2 = 2 And typ=1 And Rand(0,1)) Or ((typ2 = 4 Or typ2 = 8 Or typ2=9) And Rand(0,1))
								PokeByte bank,off,typ2
								PokeByte bank,x*sizey+sizey+y-1,typ
								typ = typ2
								moved = 1
							ElseIf Rand(0,1) And x < sizex-3
								typ2 = PeekByte(bank,x*sizey+sizey*2+y-1)
								If typ2 = 0 Or (typ2 = 2 And typ=1 And Rand(0,1)) Or ((typ2 = 4 Or typ2 = 8 Or typ2=9) And Rand(0,1))
									PokeByte bank,off,typ2
									PokeByte bank,x*sizey+sizey*2+y-1,typ
									typ = typ2
									moved = 1
								EndIf
							EndIf
						ElseIf x > 1
							typ2 = PeekByte(bank,x*sizey-sizey+y-1)
							If typ2 = 0 Or (typ2 = 2 And typ=1 And Rand(0,1)) Or ((typ2 = 4 Or typ2 = 8 Or typ2=9) And Rand(0,1))
								PokeByte bank,off,typ2
								PokeByte bank,x*sizey-sizey+y-1,typ
								typ = typ2
								moved = 1
							ElseIf Rand(0,1) And x > 2
								typ2 = PeekByte(bank,x*sizey-sizey*2+y-1)
								If typ2 = 0 Or (typ2 = 2 And typ=1 And Rand(0,1)) Or ((typ2 = 4 Or typ2 = 8 Or typ2=9) And Rand(0,1))
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
						Case 0
							If Rand(2)=2
								PokeByte bank,off,typ2
								PokeByte bank,off+1,typ
								typ = typ2
							EndIf
						Case 1,3,11
							If Rand(10)=2
								PokeByte bank,off,0
								typ = 0
							EndIf
						Case 2,12
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
							Case 0
								If Rand(20)=2
									PokeByte bank,off,typ2
									PokeByte bank,x*sizey+sizey+y-1,typ
									typ = typ2
								EndIf
							Case 1,3,11
								If Rand(10)=2
									PokeByte bank,off,0
									typ = 0
								EndIf
							Case 2,12
								If Rand(2)=1
									PokeByte bank,off,0
									typ = 0
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
							Case 0
								If Rand(20=2)
									PokeByte bank,off,typ2
									PokeByte bank,x*sizey-sizey+y-1,typ
									typ = typ2
								EndIf
							Case 1,3,11
								If Rand(10)=2
									PokeByte bank,off,0
									typ = 0
								EndIf
							Case 2,12
								If Rand(2)=1
									PokeByte bank,off,0
									typ = 0
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
							Case 0
								If Rand(10)=5
									PokeByte bank,off,typ2
									PokeByte bank,off+1,typ
									typ = typ2
								EndIf
							Case 1,3,11
								If Rand(10)=2
									PokeByte bank,off,0
									typ = 0
								EndIf
							Case 2,12
								PokeByte bank,off,0
								typ = 0
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
							Case 0
								PokeByte bank,off,typ2
								PokeByte bank,x*sizey+sizey+y,typ
								typ = typ2
							Case 1,3,11
								If Rand(10)=2
									PokeByte bank,off,0
									typ = 0
								EndIf
							Case 2,12
								If Rand(2-(typ2=12))=1
									PokeByte bank,off,0
									typ = 0
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
							Case 0
								PokeByte bank,off,typ2
								PokeByte bank,x*sizey-sizey+y,typ
								typ = typ2
							Case 1,3,11
								If Rand(10)=2
									PokeByte bank,off,0
									typ = 0
								EndIf
							Case 2,12
								If Rand(2-(typ2=12))=1
									PokeByte bank,off,0
									typ = 0
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
							If typ2 = 2
								PokeByte bank,x*sizey-sizey+y+1,typ
							ElseIf typ2 = 8 And Rand(1)
								PokeByte bank,x*sizey-sizey+y+1,0
							EndIf
						EndIf
					Case 2
						If y < sizey-2
							typ2 = PeekByte(bank,off+1)
							If typ2 = 2
								PokeByte bank,off+1,typ
							ElseIf typ2 = 8 And Rand(1)
								PokeByte bank,x*sizey+y-1,0
							EndIf
						EndIf
					Case 3
						If x < sizex-2 And y < sizey
							typ2 = PeekByte(bank,x*sizey+sizey+y+1)
							If typ2 = 2
								PokeByte bank,x*sizey+sizey+y+1,typ
							ElseIf typ2 = 8 And Rand(1)
								PokeByte bank,x*sizey+sizey+y+1,0
							EndIf
						EndIf
					Case 4
						If x > 1
							typ2 = PeekByte(bank,x*sizey-sizey+y)
							If typ2 = 2
								PokeByte bank,x*sizey-sizey+y,typ
							ElseIf typ2 = 8 And Rand(1)
								PokeByte bank,x*sizey-sizey+y,0
							EndIf
						EndIf
					Case 5
						If x < sizex-2
							typ2 = PeekByte(bank,x*sizey+sizey+y)
							If typ2 = 2
								PokeByte bank,x*sizey+sizey+y,typ
							ElseIf typ2 = 8 And Rand(1)
								PokeByte bank,x*sizey+sizey+y,0
							EndIf
						EndIf
					Case 6
						If x > 1 And y >1
							typ2 = PeekByte(bank,x*sizey-sizey+y-1)
							If typ2 = 2
								PokeByte bank,x*sizey-sizey+y-1,typ
							ElseIf typ2 = 8 And Rand(1)
								PokeByte bank,x*sizey-sizey+y-1,0
							EndIf
						EndIf
					Case 7
						If y > 1
							typ2 = PeekByte(bank,off-1)
							If typ2 = 2
								PokeByte bank,off-1,typ
							ElseIf typ2 = 8 And Rand(1)
								PokeByte bank,x*sizey+y-1,0
							EndIf
						EndIf
					Case 8
						If x < sizex-2 And y > 1
							typ2 = PeekByte(bank,x*sizey+sizey+y-1)
							If typ2 = 2
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
						If typ2 = 0
							PokeByte bank,off-1,2
						EndIf
					EndIf
				Case 8,9 ; wasser/co2-gas
					moved = 0
					typo = PeekByte(bank,off+1)
					If Rand(300)=1 And typ = 9 Then
						typ = 2
						PokeByte bank,off,typ
					Else
						If y < sizey-1 And Rand(0,6)=2 And typo = 0 ;<> 3 And typo <> 6 And typo <> 7 And typo <> 11 And typo<>12
							PokeByte bank,off,typo
							If y < sizey-2 Then PokeByte bank,off+1,typ
							typ = typo
						Else ; expandieren
							r = Rand(1,9)
							Select r
							Case 1
								If x > 0 And y < sizey-1
									typ2 = PeekByte(bank,x*sizey-sizey+y+1)
									If typ2 = 0
										PokeByte bank,x*sizey-sizey+y+1,typ
										PokeByte bank,off,typ2
										typ = typ2
									EndIf
								EndIf
							Case 2
								If y < sizey-1
									typ2 = PeekByte(bank,off+1)
									If typ2 = 0
										PokeByte bank,off+1,typ
										PokeByte bank,off,typ2
										typ = typ2
									EndIf
								EndIf
							Case 3
								If x < sizex-1 And y < sizey-1
									typ2 = PeekByte(bank,x*sizey+sizey+y+1)
									If typ2 = 0
										PokeByte bank,x*sizey+sizey+y+1,typ
										PokeByte bank,off,typ2
										typ = typ2
									EndIf
								EndIf
							Case 4
								If x > 0
									typ2 = PeekByte(bank,x*sizey-sizey+y)
									If typ2 = 0
										PokeByte bank,x*sizey-sizey+y,typ
										PokeByte bank,off,typ2
										typ = typ2
									EndIf
								EndIf
							Case 5
								If x < sizex-1
									typ2 = PeekByte(bank,x*sizey+sizey+y)
									If typ2 = 0
										PokeByte bank,x*sizey+sizey+y,typ
										PokeByte bank,off,typ2
										typ = typ2
									EndIf
								EndIf
							Case 6
								If x > 0 And y >0
									typ2 = PeekByte(bank,x*sizey-sizey+y-1)
									If typ2 = 0
										PokeByte bank,x*sizey-sizey+y-1,typ
										PokeByte bank,off,typ2
										typ = typ2
									EndIf
								EndIf
							Case 7
								If y > 0
									typ2 = PeekByte(bank,off-1)
									If typ2 = 0
										PokeByte bank,off-1,typ
										PokeByte bank,off,typ2
										typ = typ2
									EndIf
								EndIf
							Case 8
								If x < sizex-1 And y > 0
									typ2 = PeekByte(bank,x*sizey+sizey+y-1)
									If typ2 = 0
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
										If typ2 <> 0 And typ3 = 0 Then
											PokeByte bank,off+sizey,typ
											PokeByte bank,off,typ3
											typ = typ3
										ElseIf typ2 = 0 And typ3 <> 0 Then
											PokeByte bank,off-sizey,typ
											PokeByte bank,off,typ2
											typ = typ2
										ElseIf typ2 = 0 And typ3 = 0 And Rand(0,1) And y < sizey-1
											typ2 = PeekByte(bank,off+1)
											If typ2 Then
												Select Rand(0,1)
												Case 0
													PokeByte bank,off-sizey,typ
													PokeByte bank,off,0
													typ = 0
												Case 1
													PokeByte bank,off+sizey,typ
													PokeByte bank,off,0
													typ = 0
												End Select
											ElseIf y < sizey-2
												typ2 = PeekByte(bank,off+2)
												If typ2 Then
													Select Rand(0,1)
													Case 0
														PokeByte bank,off-sizey,typ
														PokeByte bank,off,0
														typ = 0
													Case 1
														PokeByte bank,off+sizey,typ
														PokeByte bank,off,0
														typ = 0
													End Select
												EndIf
											EndIf
										ElseIf typ2 <> 0 And typ3 <> 0 And x > 2 And x < sizex-3 And False
											typ2 = PeekByte(bank,off-sizey*2)
											typ3 = PeekByte(bank,off+sizey*2)
											If typ2 <> 0 And typ3 = 0 Then
												PokeByte bank,off+sizey*2,typ
												PokeByte bank,off,typ3
												typ = typ3
											ElseIf typ2 = 0 And typ3 <> 0 Then
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
										If typ2 <> 0 And typ3 = 0 Then
											PokeByte bank,off+1,typ
											PokeByte bank,off,typ3
											typ = typ3
										ElseIf typ2 = 0 And typ3 <> 0 Then
											PokeByte bank,off-1,typ
											PokeByte bank,off,typ2
											typ = typ2
										ElseIf typ2 <> 0 And typ3 <> 0 And Rand(1) And y > 2 And y < sizey-3 And False
											typ2 = PeekByte(bank,off-2)
											typ3 = PeekByte(bank,off+2)
											If typ2 <> 0 And typ3 = 0 Then
												PokeByte bank,off+2,typ
												PokeByte bank,off,typ3
												typ = typ3
											ElseIf typ2 = 0 And typ3 <> 0 Then
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
				Case 11 ; herd
					r = Rand(1,10)
					Select r
					Case 1
						If x > 1 And y < sizey-2
							typ2 = PeekByte(bank,x*sizey-sizey+y+1)
							If typ2 = 2
								PokeByte bank,x*sizey-sizey+y+1,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> 5
								PokeByte bank,x*sizey-sizey+y+1,5
							EndIf
						EndIf
					Case 2
						If y < sizey-2
							typ2 = PeekByte(bank,off+1)
							If typ2 = 2
								PokeByte bank,off+1,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> 5
								PokeByte bank,off+1,5
							EndIf
						EndIf
					Case 3
						If x < sizex-2 And y < sizey
							typ2 = PeekByte(bank,x*sizey+sizey+y+1)
							If typ2 = 2
								PokeByte bank,x*sizey+sizey+y+1,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> 5
								PokeByte bank,x*sizey+sizey+y+1,5
							EndIf
						EndIf
					Case 4
						If x > 1
							typ2 = PeekByte(bank,x*sizey-sizey+y)
							If typ2 = 2
								PokeByte bank,x*sizey-sizey+y,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> 5
								PokeByte bank,x*sizey-sizey+y,5
							EndIf
						EndIf
					Case 5
						If x < sizex-2
							typ2 = PeekByte(bank,x*sizey+sizey+y)
							If typ2 = 2
								PokeByte bank,x*sizey+sizey+y,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> 5
								PokeByte bank,x*sizey+sizey+y,5
							EndIf
						EndIf
					Case 6
						If x > 1 And y >1
							typ2 = PeekByte(bank,x*sizey-sizey+y-1)
							If typ2 = 2
								PokeByte bank,x*sizey-sizey+y-1,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> 5
								PokeByte bank,x*sizey-sizey+y-1,5
							EndIf
						EndIf
					Case 7
						If y > 1
							typ2 = PeekByte(bank,off-1)
							If typ2 = 2
								PokeByte bank,off-1,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> 5
								PokeByte bank,off-1,5
							EndIf
						EndIf
					Case 8
						If x < sizex-2 And y > 1
							typ2 = PeekByte(bank,x*sizey+sizey+y-1)
							If typ2 = 2
								PokeByte bank,x*sizey+sizey+y-1,9
							ElseIf typ2 > 1 And typ2 < 8 And typ2 <> 5
								PokeByte bank,x*sizey+sizey+y-1,5
							EndIf
						EndIf
					End Select
				Case 12 ; kühler
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
					Case 0 ; nichts
						col = 0
					Case 1 ; sand
						col = $ffffff
					Case 2 ; wasser
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
					Case 10; säure
						col = $ff7498
					Case 11; herd
						col = $ed8640
					Case 12; kühler
						col = $88ddff
					End Select
					If col WritePixelFast x,sizey-y,col
				ElseIf typo2 <> showtyp ; dunkel
					Select showtyp
					Case 0 ; nichts
						col = 0
					Case 1 ; sand
						col = $bbbbbb
					Case 2 ; wasser
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
					Case 10; säure
						col = $aa0000
					Case 11; herd
						col = $ed8640
					Case 12; kühler
						col = $88ddff
					End Select
					If col WritePixelFast x,sizey-y,col
				Else
					Select showtyp
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
			Case 0 ; nichts
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
				mat = 10
			Case $ed8640; herd
				mat = 11
			Case $88ddff; kühler
				mat = 12
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