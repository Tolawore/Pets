# Compiler settings
JAVAC = javac
JAVA = java

# Source and output directories
SRC_DIR = .
BIN_DIR = bin

# Classpath
CLASSPATH = $(BIN_DIR)

# Manifest file
MANIFEST = manifest.txt

# Jar file
JAR_FILE = Pet.jar

# Source files
SOURCES = $(wildcard $(SRC_DIR)/*.java)

# Object files
CLASSES = $(patsubst $(SRC_DIR)/%.java,$(BIN_DIR)/%.class,$(SOURCES))

# Targets
all: compile

compile: $(CLASSES)

$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	$(JAVAC) -d $(BIN_DIR) -classpath $(CLASSPATH) $<

run: compile
	$(JAVA) -classpath $(CLASSPATH) Main

jar: compile
	echo "Classpath: ." > $(MANIFEST)
	echo "Main-Class: Main" >> $(MANIFEST)
	jar cfm $(JAR_FILE) $(MANIFEST) -C $(BIN_DIR) .
	$(JAVA) -jar $(JAR_FILE)

clean:
	rm -rf $(BIN_DIR) $(JAR_FILE)

.PHONY: all compile run jar clean
