CP = LIB
SRC_PATH = FONTS
LIBS = $(CP)/byte-buddy.jar:$(CP)/commons-beanutils.jar:$(CP)/commons-collections3-3.2.2.jar:$(CP)/commons-lang-2.6.jar:$(CP)/commons-lang3.jar:$(CP)/commons-logging-1.2.jar:$(CP)/ezmorph-1.0.6.jar:$(CP)/json-lib-2.4.jar:$(CP)/junit4.jar:$(CP)/mockito-core-2.23.0.jar:$(CP)/objenesis.jar:$(CP)/opencsv.jar:$(CP)/javafx-base-11.jar
JC = javac
JAR = jar
JFLAGS =
SRC = $(wildcard $(SRC_PATH)/*.java) $(wildcard $(SRC_PATH)/domini/*.java) $(wildcard $(SRC_PATH)/vista/*.java) $(wildcard $(SRC_PATH)/dades/*.java) $(wildcard $(SRC_PATH)/util/*.java) $(wildcard $(SRC_PATH)/test/*.java) $(wildcard $(SRC_PATH)/test/runners/*.java) $(wildcard $(SRC_PATH)/drivers/*.java)
CLASS = $(SRC:java=class)
EXEC_DIR = EXE
EXEC_SRC = $(wildcard $(SRC_PATH)/test/runners/*.java) $(wildcard $(SRC_PATH)/drivers/*.java) $(SRC_PATH)/Application.java
EXEC = $(subst $(SRC_PATH), $(EXEC_DIR), $(EXEC_SRC:java=jar))

all: $(EXEC)

class: $(CLASS)

%.jar: class
	mkdir -p $(EXEC_DIR)/test; cd $(SRC_PATH); jar cfem ../$(subst $(EXEC_DIR)/Application.jar,$(EXEC_DIR)/app/Application.jar,$(subst runners/,,$@)) $(patsubst %.jar,%, $(subst $(EXEC_DIR)/,, $@)) ../manifest.txt *.class vista/*.class domini/*.class dades/*.class util/*.class test/*.class test/runners/*.class drivers/*.class

%.class: %.java
	$(JC) $(JFLAGS) -cp $(LIBS):$(SRC_PATH) $<

clean: clean-class clean-jar

clean-class:
	find $(SRC_PATH)/ -name "*.class" -type f -delete

clean-jar:
	find $(EXEC_DIR)/ -name "*.jar" -type f -delete
