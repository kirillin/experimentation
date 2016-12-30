object Form2: TForm2
  Left = 0
  Top = 0
  Caption = #1043#1083#1072#1074#1085#1086#1077' '#1086#1082#1085#1086
  ClientHeight = 333
  ClientWidth = 519
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  Menu = MainMenu1
  OldCreateOrder = False
  OnClose = FormClose
  OnCreate = FormCreate
  OnShow = FormShow
  PixelsPerInch = 96
  TextHeight = 13
  object Label1: TLabel
    Left = 8
    Top = 8
    Width = 94
    Height = 40
    Caption = 'Label1'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -33
    Font.Name = 'Tahoma'
    Font.Style = []
    ParentFont = False
  end
  object Label2: TLabel
    Left = 480
    Top = 312
    Width = 31
    Height = 13
    Alignment = taRightJustify
    Caption = 'Label2'
  end
  object MainMenu1: TMainMenu
    Left = 464
    object File1: TMenuItem
      Caption = 'File'
      object Logout1: TMenuItem
        Caption = 'Log out'
        OnClick = Logout1Click
      end
      object Exit1: TMenuItem
        Caption = 'Exit'
        OnClick = Exit1Click
      end
    end
    object Users1: TMenuItem
      Caption = 'Users'
      object New1: TMenuItem
        Caption = 'New...'
        OnClick = New1Click
      end
    end
  end
  object Timer1: TTimer
    OnTimer = Timer1Timer
    Left = 464
    Top = 56
  end
  object Timer2: TTimer
    OnTimer = Timer2Timer
    Left = 464
    Top = 112
  end
end
