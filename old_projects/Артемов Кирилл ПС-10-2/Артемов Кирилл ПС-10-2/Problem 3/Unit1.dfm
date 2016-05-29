object Form1: TForm1
  Left = 0
  Top = 0
  Caption = '3. '#1055#1077#1089#1086#1095#1085#1099#1077' '#1095#1072#1089#1099' (12 '#1073#1072#1083#1083#1086#1074')'
  ClientHeight = 350
  ClientWidth = 487
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object Image1: TImage
    Left = 88
    Top = 131
    Width = 100
    Height = 200
  end
  object Label1: TLabel
    Left = 132
    Top = 112
    Width = 3
    Height = 13
  end
  object Label2: TLabel
    Left = 88
    Top = 112
    Width = 37
    Height = 13
    Caption = #1042#1088#1077#1084#1103': '
  end
  object Label3: TLabel
    Left = 100
    Top = 32
    Width = 264
    Height = 23
    Caption = '3. '#1055#1077#1089#1086#1095#1085#1099#1077' '#1095#1072#1089#1099' (12 '#1073#1072#1083#1083#1086#1074')'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -19
    Font.Name = 'Tahoma'
    Font.Style = []
    ParentFont = False
  end
  object Button1: TButton
    Left = 88
    Top = 81
    Width = 129
    Height = 25
    Caption = #1053#1072#1095#1072#1083#1100#1085#1072#1103' '#1091#1089#1090#1072#1085#1086#1074#1082#1072
    TabOrder = 0
    OnClick = Button1Click
  end
  object Button2: TButton
    Left = 223
    Top = 81
    Width = 75
    Height = 25
    Caption = #1055#1091#1089#1082
    TabOrder = 1
    OnClick = Button2Click
  end
  object Button3: TButton
    Left = 304
    Top = 81
    Width = 75
    Height = 25
    Caption = #1042#1099#1093#1086#1076
    TabOrder = 2
    OnClick = Button3Click
  end
  object Timer1: TTimer
    Enabled = False
    Interval = 1
    OnTimer = Timer1Timer
    Left = 280
    Top = 120
  end
  object Timer2: TTimer
    Enabled = False
    OnTimer = Timer2Timer
    Left = 280
    Top = 168
  end
end
