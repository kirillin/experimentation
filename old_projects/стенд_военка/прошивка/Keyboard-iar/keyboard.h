//***************************************************************************
//
//  Author(s)...: Pashgan    http://ChipEnable.Ru   
//
//  Target(s)...: ATMega8
//
//  Compiler....: IAR 5.11A
//
//  Description.: ����� ��������� ����������. ������������� ��������� ��������.
//
//  Data........: 06.03.10 
//
//***************************************************************************
#ifndef KEYBOARD_H
#define KEYBOARD_H

#include <ioavr.h>

//������������� ������ � ���������� ����������
void InitKeyboard(void);

//������������ ����������
void ScanKeyboard(void);

//���������� ��� ������� ������
unsigned char GetKey(void);

#endif //KEYBOARD_H