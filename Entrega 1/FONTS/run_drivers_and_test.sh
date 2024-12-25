#!/bin/bash

# Directory paths
SRC_DIR="src"
DRIVERS_DIR="$SRC_DIR/drivers"
TEST_DIR="$SRC_DIR/test"
BUILD_DIR="build"

# JUnit jar paths (make sure these exist in your lib directory)
JUNIT_JAR="../lib/junit-4.13.1.jar"
HAMCREST_JAR="../lib/hamcrest-core-1.3.jar"

# Create build directory if it doesn't exist
mkdir -p $BUILD_DIR

# Function to compile and run a specific driver
run_driver() {
    driver_name=$1
    
    # Check if the driver file exists
    if [ ! -f "$DRIVERS_DIR/$driver_name.java" ]; then
        echo "Error: Driver $driver_name.java not found in $DRIVERS_DIR"
        exit 1
    fi
    
    echo "Compiling $driver_name.java..."
    javac -d $BUILD_DIR -cp "$SRC_DIR" "$DRIVERS_DIR/$driver_name.java"
    
    if [ $? -eq 0 ]; then
        echo "Running $driver_name..."
        java -cp $BUILD_DIR "drivers.$driver_name"
    else
        echo "Compilation failed!"
        exit 1
    fi
}

# Function to run all tests using TestMaster
run_tests() {
    echo "Compiling tests..."
    # Compile all source files including tests
    javac -d $BUILD_DIR -cp "$SRC_DIR:$JUNIT_JAR:$HAMCREST_JAR" \
        $(find $SRC_DIR -name "*.java")
    
    if [ $? -eq 0 ]; then
        echo "Running tests..."
        java -cp "$BUILD_DIR:$JUNIT_JAR:$HAMCREST_JAR" org.junit.runner.JUnitCore test.TestMaster
    else
        echo "Test compilation failed!"
        exit 1
    fi
}

# Show help menu
show_help() {
    echo "Usage:"
    echo "  $0 [option]"
    echo ""
    echo "Options:"
    echo "  -d, --driver <DriverName>  Run a specific driver"
    echo "  -t, --test                 Run all tests using TestMaster"
    echo "  -l, --list                 List available drivers"
    echo "  -h, --help                 Show this help message"
    echo ""
    echo "Examples:"
    echo "  $0 --driver CtrlDomainDriver"
    echo "  $0 --test"
    echo "  $0 --list"
}

# List available drivers
list_drivers() {
    echo "Available drivers:"
    for file in $DRIVERS_DIR/*.java; do
        if [ -f "$file" ]; then
            basename "$file" .java
        fi
    done
}

# Parse command line arguments
case "$1" in
    -d|--driver)
        if [ -z "$2" ]; then
            echo "Error: Driver name required"
            show_help
            exit 1
        fi
        run_driver "$2"
        ;;
    -t|--test)
        run_tests
        ;;
    -l|--list)
        list_drivers
        ;;
    -h|--help)
        show_help
        ;;
    "")
        show_help
        ;;
    *)
        echo "Error: Unknown option $1"
        show_help
        exit 1
        ;;
esac