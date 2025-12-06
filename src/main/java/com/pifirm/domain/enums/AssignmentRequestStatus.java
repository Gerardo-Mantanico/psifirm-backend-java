package com.pifirm.domain.enums;

import java.util.Objects;
import java.util.Set;

public enum AssignmentRequestStatus {
    PENDING,
    ACCEPTED,
    REJECTED,
    CANCELLED;

    public static boolean canBeCancelled(AssignmentRequestStatus status) {
        return Objects.equals(PENDING, status);
    }

    public static boolean canBeAccepted(AssignmentRequestStatus status) {
        return Objects.equals(PENDING, status);
    }

    public static boolean canBeRejected(AssignmentRequestStatus status) {
        return Objects.equals(PENDING, status);
    }
}
