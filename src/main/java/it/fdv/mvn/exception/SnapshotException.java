package it.fdv.mvn.exception;

public class SnapshotException extends Exception {

    public SnapshotException() {
        super("The version attempting to augment is a SNAPSHOT, use the -Dforce option to force augmentation");
    }
}
