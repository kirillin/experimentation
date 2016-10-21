//******************************************************************************
//
//  Author(s)...: Pashgan    http://ChipEnable.Ru   
//
//  Target(s)...: �����
//
//  Compiler....: �����
//
//  Description.: ������������� ������� ��������� ����������
//
//  Data........: 12.10.2011
//
//******************************************************************************
#ifndef KEYBOARD_H
#define KEYBOARD_H

//������ ��� ��������� ������������*********************************************

//�������� ������� ����������������
#ifndef F_CPU
#define F_CPU 8000000
#endif

//���������� ��� ����������
//���� ���������������� ��� �������
//����� ������ ���������� 3�4
//#define KEYBOARD_4X4

//������������ �� ����� ����
//���� ���������������� - �� ��������� ������
//�� ����� ����������� ����� ������� ����������
//� ����������������� �����
//#define COMMON_BUS

//����, � �������� �����. ������
#define PORTX_ROW PORTC
#define PINX_ROW  PINC
#define DDRX_ROW  DDRC

//����, � �������� �����. �������
#define PORTX_COL PORTC
#define PINX_COL  PINC
#define DDRX_COL  DDRC

//������ ��, � ������� ���������� 
//������ ��������� ����������
#define PIN_ROW1 3
#define PIN_ROW2 4
#define PIN_ROW3 5
#define PIN_ROW4 6

//������ ��, � ������� ����������
//������� ��������� ����������
#define PIN_COL1 0
#define PIN_COL2 1
#define PIN_COL3 2
#define PIN_COL4 

//���� ������
#define EVENT_NULL 0
#define EVENT_KEY0 0
#define EVENT_KEY1 1
#define EVENT_KEY2 2
#define EVENT_KEY3 3
#define EVENT_KEY4 4
#define EVENT_KEY5 5
#define EVENT_KEY6 6
#define EVENT_KEY7 7
#define EVENT_KEY8 8
#define EVENT_KEY9 9
#define EVENT_KEYA 'A'
#define EVENT_KEYB 'B'
#define EVENT_KEYC 'C'
#define EVENT_KEYD 'D'
#define EVENT_KEYZ '*'
#define EVENT_KEYR '#'


//���������������� �������******************************************************

void KEYB_Init(void);                  //�������������
void KEYB_ScanKeyboard(void);          //������������ ����������
unsigned char KEYB_GetKey(void);       //����� ��� ������� ������

#endif //KEYBOARD_H