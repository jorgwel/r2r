#!/bin/sh
MISCONFIGURATION_ERROR=3
PLANT_COMMAND="java -jar $PLANTUML "
DESTINATION_FOLDER="diagrams"

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
	echo "PlantUML is not installed or environment variable $PLANTUML is not pointing to the absolute path of the plantuml jar. If you haven't downloaded PlantUML, you can do it from here http://plantuml.com/"
        exit MISCONFIGURATION_ERROR
    fi
}

verify_java_and_plantuml_play_well () {
    text_present_in_good_plantuml_configuration="Installation seems OK. File generation OK"
    number_of_lines=`java -jar "$PLANTUML" -version 2>&1 | grep "$text_present_in_good_plantuml_configuration" | wc -l`
    if [ "" != "$number_of_lines"  -a  "$number_of_lines" -ne 1 ]; then
        echo "Java and plantuml path '$PLANTUML' are not playing well together"
        exit MISCONFIGURATION_ERROR
    fi
}

#get every .puml file and generate the diagram in the folder diagrams
generate_diagrams_for_each_plantuml_file () {
    list_of_plantuml_files=`ls *.puml | tr ' ' '\n'`
#   list_of_modified_plantuml_files=`git ls-files -m | grep puml`
    for plantuml_file in $(echo "$list_of_plantuml_files"); do
	echo -n "Generating diagrams for file \"$plantuml_file\"..."
	`$PLANT_COMMAND $plantuml_file -svg -o $DESTINATION_FOLDER`
	echo "Done"
    done
}


verify_java_can_be_executed
verify_plantuml_path_existence
verify_java_and_plantuml_play_well
generate_diagrams_for_each_plantuml_file

exit 0
