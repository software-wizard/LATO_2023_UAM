// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class HamDecorator extends Pizza {
    private final Pizza decorated;

    public HamDecorator(Pizza aPizza) {
        decorated = aPizza;
    }

    @Override
    int getPrice() {
        return decorated.getPrice() + 4;
    }
}
