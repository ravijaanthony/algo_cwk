# Maze Traversal Using A* Algorithm

This project implements a solution to traverse through a maze with icy floors and obstacles using the A* (A-star) algorithm. The goal is to find the shortest path from the starting point (S) to the ending point (F) in the maze.

## Introduction

This Java application solves the problem of navigating through a maze represented as a 2D grid, where:
- 'S' represents the starting point.
- 'F' represents the ending point.
- '0' represents obstacles that cannot be traversed.
- '.' Other cells represent icy floors that can be traversed but with varying costs.

The A* algorithm was chosen for its efficiency in finding the shortest path in such environments.

## Features

- **A* Algorithm**: Utilizes A* (A-star) algorithm to find the shortest path from start to finish.
- **Heuristic Function**: Employs a heuristic function (Manhattan distance or Euclidean distance) to guide the search efficiently.
- **Maze Representation**: Reads maze configurations from a text file for easy customization.

## Setup

To run this project, ensure you have Java Development Kit (JDK) installed on your machine.

1. Clone this repository: git clone https://github.com/ravijaanthony/algo_cwk.git
2. Navigate to the project directory: cd algo_cwk
3. Compile the Java files: javac *.java
4. OR run it in your IDE

Running the program would output the shortest path from 'S' to 'F' in the maze.

## Credits

This project was developed by Ravija Anthony as coursework for DSA-UoW/IIT.
