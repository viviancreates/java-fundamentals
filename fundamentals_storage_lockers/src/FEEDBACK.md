This is a solid submission! The separation of concerns is good with distinct classes for different responsibilities as well as good encapsulation of fields (getters and setters) and logical method names. I think you're getting object oriented design down well. This would be marked as passed if not for the pin and menu bugs.

**OVERALL: REVISION REQUEST**

* **Setter exposure**: `setPin()` and `setLockerNumber()` shouldn't be public - they break encapsulation since these should only be set through `rentLocker()`

* **PIN generation bug**: `String.format("%02d", (int) (Math.random() * 10000))` only formats 2 digits but should be 4

* **Code duplication**: PIN validation logic repeated in multiple methods

* **Early exit bug**: `break;` statement exits the program on invalid choice instead of continuing the loop.

  ```java
  // Current:
  } else {
      io.print("Invalid choice. Pick again pls.");
      break;  // This exits the program!
  }
  
  // Fixed:
  } else {
      io.print("Invalid choice. Please try again.");
      // Remove break - let loop continue
  }
  ```

Here are some general style things. No need to fix these today, but next summative assessment please check for:

* **Excessive comments**: Remove commented code blocks and reduce inline comments. Code should be self-documenting
* **Inconsistent spacing**: Add consistent spacing around operators and after commas
* **Debug print statements**: Remove `io.print("say hello and rent");` etc. before submitting
* **Error/Output Language**: Avoid casual language in business applications, "Pick again pls". *smile_emoji*