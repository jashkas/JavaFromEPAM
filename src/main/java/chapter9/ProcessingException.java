package chapter9;

public class ProcessingException extends Exception {
    public ProcessingException(String message) {
        super(message);
    }

    public static class OutOfMemoryProcessingException extends ProcessingException {
        public OutOfMemoryProcessingException(String message) {
            super(message);
        }
    }

    public static class FileNotFoundProcessingException extends ProcessingException {
        public FileNotFoundProcessingException(String message) {
            super(message);
        }
    }

    public static class InvalidNumberFormatProcessingException extends ProcessingException {
        public InvalidNumberFormatProcessingException(String message) {
            super(message);
        }
    }

    public static class NumberOutOfRangeProcessingException extends ProcessingException {
        public NumberOutOfRangeProcessingException(String message) {
            super(message);
        }
    }
}
