//
// Created by 19093 on 2023/11/12.
//

#ifndef IPI_GETPI_H
#define IPI_GETPI_H

int long calculateFactorial(int n) {
    long factorial = 1;
    for (int i = 1; i <= n; ++i)
        factorial *= i;
    return factorial;
}
#endif //IPI_GETPI_H
