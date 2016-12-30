object Form3: TForm3
  Left = 0
  Top = 0
  Caption = #1054#1082#1085#1086' '#1072#1074#1090#1086#1088#1080#1079#1072#1094#1080#1080
  ClientHeight = 202
  ClientWidth = 459
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  OldCreateOrder = False
  OnCreate = FormCreate
  OnShow = FormShow
  PixelsPerInch = 96
  TextHeight = 13
  object Label1: TLabel
    Left = 88
    Top = 51
    Width = 34
    Height = 13
    Caption = #1051#1086#1075#1080#1085':'
  end
  object Label2: TLabel
    Left = 87
    Top = 78
    Width = 35
    Height = 13
    Caption = #1060#1088#1072#1079#1072':'
  end
  object Label3: TLabel
    Left = 370
    Top = 102
    Width = 31
    Height = 13
    Alignment = taRightJustify
    Caption = 'Label3'
  end
  object Label4: TLabel
    Left = 370
    Top = 121
    Width = 31
    Height = 13
    Alignment = taRightJustify
    Caption = 'Label4'
  end
  object Edit1: TEdit
    Left = 128
    Top = 48
    Width = 153
    Height = 21
    TabOrder = 0
    Text = 'Edit1'
    OnChange = Edit1Change
  end
  object Edit2: TEdit
    Left = 128
    Top = 75
    Width = 273
    Height = 21
    TabOrder = 1
    Text = 'Edit2'
    OnClick = Edit2Click
    OnEnter = Edit2Enter
    OnKeyPress = Edit2KeyPress
  end
  object Button1: TButton
    Left = 133
    Top = 136
    Width = 75
    Height = 25
    Caption = #1042#1086#1081#1090#1080
    TabOrder = 2
    OnClick = Button1Click
  end
  object Button2: TButton
    Left = 214
    Top = 136
    Width = 187
    Height = 25
    Caption = #1057#1086#1079#1076#1072#1090#1100' '#1085#1086#1074#1086#1075#1086' '#1087#1086#1083#1100#1079#1086#1074#1072#1090#1077#1083#1103
    TabOrder = 3
    OnClick = Button2Click
  end
  object Timer1: TTimer
    Enabled = False
    Interval = 10
    OnTimer = Timer1Timer
    Left = 392
    Top = 8
  end
end
