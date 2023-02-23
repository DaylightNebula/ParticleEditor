The goal of this is to be a simple file editor with plugin options.
This is meant to be a simple open source alternative to editors like VS Code and <insert jetbrains version> that could be used in both personal and commercial projects.

For example, a plugin for json files could be easily added that would allow for completion and organize text by colors.
Or an object renderer could be easily implemented to render a 3d object with a custom file type.

=== Todo List (here cause Im lazy) ===
- [x] Basic Editor
  - [x] Top bar with an optional list of buttons
    - [x] Open project button
  - [x] File tree on the left side
  - [x] Easy way to create dropdown menus
  - [x] Tab rendering
    - [x] Tabbed file viewer
    - [x] Open from file tree
  - [x] Default text tab
    - [x] Load into individual lines
    - [x] Draw lines with line numbers as individual text fields
    - [x] Make text box darker than the standard background
    - [x] When a line is edited
      - [x] Remove line if necessary
      - [x] Save the changes after the callback is finished
- [ ] Plugin System
  - [x] Options to add render top bar composable (gradle button for example)
  - [x] File types list (for example .gradle and .gradle.kts for gradle plugin)
  - [x] Tab composable render function
  - [ ] Allow plugins to change color of file names in the list
  - [ ] Allow plugins to define an image to draw next to an image
  - [ ] Event when a file changes
  - [ ] Overlay menus
  - [x] Reload plugins button
- [ ] Base file types
  - [ ] .json
  - [ ] .properties
- [ ] GitHub releases