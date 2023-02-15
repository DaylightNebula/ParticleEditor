The goal of this is to be a simple file editor with plugin options.
This is meant to be a simple open source alternative to editors like VS Code and Flutter that could be used in both personal and commercial projects.

For example, a plugin for json files could be easily added that would allow for completion and organize text by colors.
Or an object renderer could be easily implemented to render a 3d object with a custom file type.

=== Todo List ===
- [ ] Basic Editor
  - [ ] Top bar with an optional list of buttons
  - [ ] File tree on the left side
  - [ ] Easy way to create overlay menus
  - [ ] Tab rendering
    - [ ] Default text tab (find someway of making this available to plugins)
- [ ] Plugin System
  - [ ] Options to add render top bar composable (gradle button for example)
  - [ ] File types list (for example .gradle and .gradle.kts for gradle plugin)
  - [ ] Tab composable render function
  - [ ] Allow plugins to change color of file names in the list
  - [ ] Allow plugins to define an image to draw next to an image
  - [ ] Event when a file changes
  - [ ] Overlay menus