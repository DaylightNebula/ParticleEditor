The goal of this is to be a simple file editor with plugin options.
This is meant to be a simple open source alternative to editors like VS Code and <insert jetbrains version> that could be used in both personal and commercial projects.

For example, a plugin for json files could be easily added that would allow for completion and organize text by colors.
Or an object renderer could be easily implemented to render a 3d object with a custom file type.

=== Todo List ===
- [x] Basic Editor
  - [x] Top bar with an optional list of buttons
    - [x] Open project button
  - [x] File tree on the left side
  - [x] Easy way to create dropdown menus
  - [x] Tab rendering
    - [x] Tabbed file viewer
    - [x] Open from file tree
  - [ ] Default text tab
    - [ ] Load into individual lines
    - [ ] Pass lines to a callback for formatting while loading
    - [ ] Draw lines with line numbers as individual text fields
    - [ ] Make text box darker than the standard background
    - [ ] When a line is edited
      - [ ] Call a callback with the changes
      - [ ] Save the changes after the callback is finished
- [ ] Plugin System
  - [ ] Options to add render top bar composable (gradle button for example)
  - [ ] File types list (for example .gradle and .gradle.kts for gradle plugin)
  - [ ] Tab composable render function
  - [ ] Allow plugins to change color of file names in the list
  - [ ] Allow plugins to define an image to draw next to an image
  - [ ] Event when a file changes
  - [ ] Overlay menus
  - [ ] Reload plugins button