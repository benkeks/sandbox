BCC      = blitzccOld
CFLAGS   = 
MAINFILE = main.bb
EXENAME  = sand.exe
FMODDLL  = fmod.dll
RELEASENAME = sandbox
RELEASEVERSION  = 0.2.2
RELEASEDIR = release/$(RELEASENAME)

run: $(MAINFILE)
	$(BCC) $(CFLAGS) $(MAINFILE)

debugfullrun: $(MAINFILE)
	$(BCC) $(CFLAGS) -d $(MAINFILE)

build: $(MAINFILE)
	$(BCC) $(CFLAGS) -o $(EXENAME) $(MAINFILE)

release: build
	rm -rf $(RELEASEDIR)
	mkdir -p $(RELEASEDIR)/src
	cp 0.sand 1.sand draw.bb gui.bb main.bb Makefile objects.bb sand.bb $(RELEASEDIR)/src/ && \
	cp $(EXENAME) screenshot.png README.md LICENSE $(RELEASEDIR) && \
	cp $(FMODDLL) $(RELEASEDIR)
	
releaseitch:
	make release
	butler push $(RELEASEDIR) benkeks/$(RELEASENAME):win32 --userversion $(RELEASEVERSION)

clean:
	rm -rf $(RELEASEDIR)
	rm -f $(EXENAME)
