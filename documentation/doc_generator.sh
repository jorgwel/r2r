#!/bin/sh
MISCONFIGURATION_ERROR=3

verify_java_can_be_executed () { 
    number_of_lines=`java -version 2>&1 | grep "Runtime Environment" | wc -l`
    if [ "" != "$number_of_lines"  -a  "$number_of_lines" -ne 1 ]; then
        echo "Java is not installed correctly in this computer"
        exit MISCONFIGURATION_ERROR
    fi
}

verify_plantuml_path_existence () { 
    number_of_lines=`echo $PLANTUML | wc -l`
    if [ $number_of_lines -ne 1 ]; then
        exit MISCONFIGURATION_ERROR
    fi
}

verify_java_and_plantuml_play_well () { 
    number_of_lines=`java -jar "$PLANTUML" -version 2>&1 | grep "Installation seems OK. File generation OK" | wc -l`
    if [ "" != "$number_of_lines"  -a  "$number_of_lines" -ne 1 ]; then
        echo "Java and plantuml path '$PLANTUML' are not playing well together"
        exit MISCONFIGURATION_ERROR
    fi
}

#get every .puml file and generate the diagram in the folder diagrams


verify_java_can_be_executed
verify_plantuml_path_existence
verify_java_and_plantuml_play_well

exit 0
