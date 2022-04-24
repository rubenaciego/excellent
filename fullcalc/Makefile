CP = /usr/share/java
SRC_PATH = src
LIBS = $(CP)/byte-buddy.jar:$(CP)/commons-beanutils.jar:$(CP)/commons-collections3.jar:$(CP)/commons-lang-2.6.jar:$(CP)/commons-logging.jar:$(CP)/ezmorph.jar:$(CP)/json-lib.jar:$(CP)/junit4.jar:$(CP)/mockito-core.jar:$(CP)/objenesis.jar
JC = javac
JAR = jar
JFLAGS =
SRC = $(wildcard src/domini/*.java) $(wildcard src/test/*.java) $(wildcard src/test/runners/*.java) $(wildcard src/drivers/*.java)
CLASS = $(SRC:java=class)
EXEC_DIR = exec
EXEC_SRC = $(wildcard src/test/runners/*.java) $(wildcard src/drivers/*.java)
EXEC = $(subst src, $(EXEC_DIR), $(EXEC_SRC:java=jar))

all: $(EXEC)

class: $(CLASS)

%.jar: class
	cd src; jar cfem ../$@ $(patsubst %.jar,%, $(subst exec/,, $@)) ../manifest.txt domini/*.class test/*.class test/runners/*.class drivers/*.class

%.class: %.java
	$(JC) $(JFLAGS) -cp $(LIBS):$(SRC_PATH) $<

clean: clean-class clean-jar

clean-class:
	find $(SRC_PATH)/ -name "*.class" -type f -delete

clean-jar:
	find $(EXEC_DIR)/ -name "*.jar" -type f -delete
